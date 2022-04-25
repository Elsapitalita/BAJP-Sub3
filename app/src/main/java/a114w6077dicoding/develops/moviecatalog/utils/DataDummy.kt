@file:Suppress("unused")

package a114w6077dicoding.develops.moviecatalog.utils

import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import android.util.Log
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

object DataDummy {

    private var data = Gson()

    class ListMovieEntity {
        lateinit var results: List<MovieEntity>
    }

    class ListTvEntity{
        lateinit var results: List<TvShowEntity>
    }

    fun getDummyMovies() = data.fromJson(loadJSON("movie.json"), ListMovieEntity::class.java).results
    fun getDummyMoviesDetail(): MovieEntity = data.fromJson(loadJSON("movie_detail.json"), MovieEntity::class.java)
    fun getDummyTv() = data.fromJson(loadJSON("tv.json"), ListTvEntity::class.java).results
    fun getDummyTvDetail(): TvShowEntity = data.fromJson(loadJSON("tv_detail.json"), TvShowEntity::class.java)
    private fun loadJSON(filename: String): String?{
        var json: String? = null
        try {
            val input : InputStream = this.javaClass.classLoader!!.getResourceAsStream(filename)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer, charset("UTF-8"))
        }catch (ex: IOException){
            Log.e("Dummy", ex.toString())
        }
        return json
    }
}