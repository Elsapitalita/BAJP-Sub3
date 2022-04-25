package a114w6077dicoding.develops.moviecatalog.data.source.remote

import a114w6077dicoding.develops.moviecatalog.data.model.Movie
import a114w6077dicoding.develops.moviecatalog.data.model.MovieResults
import a114w6077dicoding.develops.moviecatalog.data.model.Tv
import a114w6077dicoding.develops.moviecatalog.data.model.TvResults
import a114w6077dicoding.develops.moviecatalog.data.remote.ApiResponse
import a114w6077dicoding.develops.moviecatalog.network.ApiConfig
import a114w6077dicoding.develops.moviecatalog.utils.Constanta
import a114w6077dicoding.develops.moviecatalog.utils.EspressoIdlingResource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovies(): LiveData<ApiResponse<List<MovieResults>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResults>>>()
        ApiConfig.getApiService().getMovies()
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        val result = response.body()?.results
                        if (result != null)
                            resultMovie.value = ApiResponse.success(result)
                        Log.d(Constanta.SUCCESS, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d(Constanta.FAILED, t.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        return resultMovie
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MovieResults>>{
        EspressoIdlingResource.increment()
        val movieDetail = MutableLiveData<ApiResponse<MovieResults>>()
        ApiConfig.getApiService()
            .getDetailMovie(movieId)
            .enqueue(object : Callback<MovieResults> {
                override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
                    val result = response.body()
                    if (result != null)
                        movieDetail.value = ApiResponse.success(result)
                    Log.d(Constanta.SUCCESS, response.code().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                    Log.d(Constanta.FAILED, t.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        return movieDetail
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvResults>>> {
        EspressoIdlingResource.increment()
        val tvResult = MutableLiveData<ApiResponse<List<TvResults>>>()
        ApiConfig.getApiService().getTvShowData()
            .enqueue(object : Callback<Tv>{
                override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                    val result = response.body()?.results
                    if (result != null){
                        tvResult.value = ApiResponse.success(result)
                    }
                    Log.d(Constanta.SUCCESS, response.code().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<Tv>, t: Throwable) {
                    Log.d(Constanta.FAILED, t.message.toString())
                    EspressoIdlingResource.decrement()
                }

            })
        return tvResult
    }

    fun getTvDetail(tvId: Int) : LiveData<ApiResponse<TvResults>>{
        EspressoIdlingResource.increment()
        val tvDetail = MutableLiveData<ApiResponse<TvResults>>()
        ApiConfig.getApiService().getDetailTv(tvId)
            .enqueue(object : Callback<TvResults> {
                override fun onResponse(call: Call<TvResults>, response: Response<TvResults>) {
                    val result = response.body()
                    if (result!= null)
                        tvDetail.value = ApiResponse.success(result)
                    Log.d(Constanta.SUCCESS, response.code().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvResults>, t: Throwable) {
                    Log.d(Constanta.FAILED, t.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        return tvDetail
    }
    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource()
        }
    }
}