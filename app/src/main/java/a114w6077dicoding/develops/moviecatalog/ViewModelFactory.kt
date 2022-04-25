package a114w6077dicoding.develops.moviecatalog

import a114w6077dicoding.develops.moviecatalog.data.MovieCatalogueRepository
import a114w6077dicoding.develops.moviecatalog.ui.movie.MovieViewModel
import a114w6077dicoding.develops.moviecatalog.ui.tv.TvShowViewModel
import a114w6077dicoding.develops.moviecatalog.utils.Injection
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java)
            -> {
                MovieViewModel(movieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java)
            -> {
                TvShowViewModel(movieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
        companion object{
            @Volatile
            private var instance : ViewModelFactory? = null

            fun getInstance(context : Context) : ViewModelFactory = instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
        }

}
