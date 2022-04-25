package a114w6077dicoding.develops.moviecatalog.data

import a114w6077dicoding.develops.moviecatalog.data.remote.ApiResponse
import a114w6077dicoding.develops.moviecatalog.data.remote.StatusResponse
import a114w6077dicoding.develops.moviecatalog.utils.AppExecutors
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBoundResources<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init{
        result.value = Resource.loading(null)

        val dbSource = loadFromDB()

        result.addSource(dbSource){
            data -> result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource)
            } else{
                result.addSource(dbSource){
                    newData -> result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)


    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.statusResponse) {
                StatusResponse.SUCCESS ->
                    mExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

}