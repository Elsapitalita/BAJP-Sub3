package a114w6077dicoding.develops.moviecatalog.data

import a114w6077dicoding.develops.moviecatalog.data.database.MovieCatalogueDao
import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import androidx.lifecycle.LiveData
import androidx.paging.DataSource

class LocalDataSource(private val movieCatalogueDao: MovieCatalogueDao) {

    fun getMovies():  DataSource.Factory<Int, MovieEntity> = movieCatalogueDao.getMovie()

    fun getDetailMovie(id: Int): LiveData<MovieEntity> = movieCatalogueDao.getMovieById(id)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = movieCatalogueDao.getFavoriteMovie()

    fun insertMovie(movie: List<MovieEntity>) = movieCatalogueDao.insertMovies(movie)

    fun setFavoriteMovie(movie: MovieEntity, favorite: Boolean){
        movie.favorite = favorite
        movieCatalogueDao.updateMovies(movie)
    }

    fun searchMovie(title: String): DataSource.Factory<Int, MovieEntity> = movieCatalogueDao.searchMovie(title)

    fun getTvShow(): DataSource.Factory<Int, TvShowEntity> = movieCatalogueDao.getTvShow()

    fun getDetailTv(id: Int): LiveData<TvShowEntity> = movieCatalogueDao.getTvShowById(id)

    fun getAllFavoriteTv(): DataSource.Factory<Int, TvShowEntity> = movieCatalogueDao.getFavoriteTvShow()

    fun insertTv(tv: List<TvShowEntity>) = movieCatalogueDao.insertTvShow(tv)

    fun setFavoriteTv(tv: TvShowEntity, favorite: Boolean){
        tv.favorite = favorite
        movieCatalogueDao.updateTvShow(tv)
    }
    fun searchTv(title: String) =  movieCatalogueDao.searchTvShow(title)


    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)
    }
}