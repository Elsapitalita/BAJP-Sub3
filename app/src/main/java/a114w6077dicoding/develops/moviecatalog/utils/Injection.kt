package a114w6077dicoding.develops.moviecatalog.utils

import a114w6077dicoding.develops.moviecatalog.data.LocalDataSource
import a114w6077dicoding.develops.moviecatalog.data.MovieCatalogueRepository
import a114w6077dicoding.develops.moviecatalog.data.database.MovieCatalogueDatabase
import a114w6077dicoding.develops.moviecatalog.data.source.remote.RemoteDataSource
import android.content.Context

object Injection {
    fun provideRepository(context:Context): MovieCatalogueRepository{
        val database = MovieCatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieCatalogueDao())
        val appExecutors = AppExecutors()

        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}