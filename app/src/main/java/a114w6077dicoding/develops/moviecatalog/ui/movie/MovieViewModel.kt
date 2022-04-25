package a114w6077dicoding.develops.moviecatalog.ui.movie

import a114w6077dicoding.develops.moviecatalog.data.MovieCatalogueRepository
import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private  var movieId = MutableLiveData<Int>()

    fun setMovieId(idMovie: Int){
        this.movieId.value = idMovie
    }

    fun getMovie() : LiveData<Resource<PagedList<MovieEntity>>> = movieCatalogueRepository.getMovies()

    var getMovieDetail : LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId){
        movieCatalogueRepository.getDetailMovie(it)
    }

    fun getMovieFavorite(): LiveData<PagedList<MovieEntity>> = movieCatalogueRepository.getFavoriteMovie()

    fun setFavorite(){
        val favoriteResource = getMovieDetail.value
        if (favoriteResource != null ){
            val favoriteMovie = favoriteResource.data

            if (favoriteMovie != null){
                val state =!favoriteMovie.favorite
                movieCatalogueRepository.setFavoriteMovie(favoriteMovie, state)
            }
        }
    }

    fun searchMovie(title: String): LiveData<PagedList<MovieEntity>> = movieCatalogueRepository.searchMovie(title)
}