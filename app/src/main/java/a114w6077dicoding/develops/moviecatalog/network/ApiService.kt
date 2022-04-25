package a114w6077dicoding.develops.moviecatalog.network

import a114w6077dicoding.develops.moviecatalog.BuildConfig
import a114w6077dicoding.develops.moviecatalog.data.model.Movie
import a114w6077dicoding.develops.moviecatalog.data.model.MovieResults
import a114w6077dicoding.develops.moviecatalog.data.model.Tv
import a114w6077dicoding.develops.moviecatalog.data.model.TvResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.ENGLISH_US,
        @Query("page") page: Int = 1
    ):Call<Movie>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<MovieResults>


    @GET("discover/tv")
    fun getTvShowData(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.ENGLISH_US,
        @Query("page") page: Int = 1,
    ): Call<Tv>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<TvResults>
}
