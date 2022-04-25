package a114w6077dicoding.develops.moviecatalog.data.source

import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface MovieCatalogueDataSource {
    fun getMovies() : LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId : Int): LiveData<Resource<MovieEntity>>
    fun getTvShows() : LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShow(tvShowId: Int) : LiveData<Resource<TvShowEntity>>
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
    fun searchMovie(title: String): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, favorite: Boolean)
    fun getFavoriteTv(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteTv(tv: TvShowEntity, favorite: Boolean)
    fun searchTv(title: String): LiveData<PagedList<TvShowEntity>>
}