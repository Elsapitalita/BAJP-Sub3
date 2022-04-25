package a114w6077dicoding.develops.moviecatalog.data.remote

class ApiResponse<T>(val statusResponse: StatusResponse, val body: T, val message: String?) {
    companion object{
        fun <T> success(body:T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)
        fun <T> error(message: String?, body:T): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, message)
    }
}