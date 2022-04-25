package a114w6077dicoding.develops.moviecatalog.ui.tv

import a114w6077dicoding.develops.moviecatalog.data.MovieCatalogueRepository
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private  var tvShowId = MutableLiveData<Int>()

    fun setTvShowId(tvId: Int){
        this.tvShowId.value = tvId
    }

    fun getTvShow() : LiveData<Resource<PagedList<TvShowEntity>>> = movieCatalogueRepository.getTvShows()

    var getTvShowDetail : LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId){
        movieCatalogueRepository.getDetailTvShow(it)
    }

    fun getTvShowFavorite(): LiveData<PagedList<TvShowEntity>> = movieCatalogueRepository.getFavoriteTv()

    fun setFavorite(){
        val favoriteResource = getTvShowDetail.value
        if (favoriteResource != null ){
            val favoriteTvShow = favoriteResource.data

            if (favoriteTvShow != null){
                val state =!favoriteTvShow.favorite
                movieCatalogueRepository.setFavoriteTv(favoriteTvShow, state)
            }
        }
    }

    fun searchTvShow(title: String): LiveData<PagedList<TvShowEntity>> = movieCatalogueRepository.searchTv(title)
}