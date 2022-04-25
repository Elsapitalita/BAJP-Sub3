package a114w6077dicoding.develops.moviecatalog.data

import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import a114w6077dicoding.develops.moviecatalog.data.model.MovieResults
import a114w6077dicoding.develops.moviecatalog.data.model.TvResults
import a114w6077dicoding.develops.moviecatalog.data.remote.ApiResponse
import a114w6077dicoding.develops.moviecatalog.data.source.MovieCatalogueDataSource
import a114w6077dicoding.develops.moviecatalog.data.source.remote.RemoteDataSource
import a114w6077dicoding.develops.moviecatalog.utils.AppExecutors
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class DummyMovieCatalogueRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {
    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResources<PagedList<MovieEntity>, List<MovieResults>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }


            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieResults>>> =
                remoteDataSource.getMovies()


            override fun saveCallResult(data: List<MovieResults>) {
                val movieList = ArrayList<MovieEntity>()
                for (movieResponse in data) {
                    val movie = MovieEntity(
                        id = movieResponse.id,
                        release_date = movieResponse.releaseDate,
                        title = movieResponse.title,
                        overview = movieResponse.overview,
                        poster_path = movieResponse.poster_path,
                        vote_average = movieResponse.voteAverage,
                        favorite = false)
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)

            }

        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResources<MovieEntity, MovieResults>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieResults>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MovieResults) {

            }

        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResources<PagedList<TvShowEntity>, List<TvResults>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvResults>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvResults>) {
                val movieList = ArrayList<TvShowEntity>()
                for (tvResponse in data) {
                    val movie = TvShowEntity(
                        id = tvResponse.id,
                        first_air_date = tvResponse.first_air_date,
                        original_name = tvResponse.original_name,
                        overview = tvResponse.overview,
                        poster_path = tvResponse.poster_path,
                        vote_average = tvResponse.vote_average,
                        favorite = false)
                    movieList.add(movie)
                }
                localDataSource.insertTv(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResources<TvShowEntity, TvResults>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getDetailTv(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvResults>> =
                remoteDataSource.getTvDetail(tvShowId)

            override fun saveCallResult(data: TvResults) {

            }

        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun searchMovie(title: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.searchMovie(title), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, favorite: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, favorite) }


    override fun getFavoriteTv(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteTv(), config).build()
    }

    override fun setFavoriteTv(tv: TvShowEntity, favorite: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTv(tv, favorite) }


        override fun searchTv(title: String): LiveData<PagedList<TvShowEntity>> {
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
            return LivePagedListBuilder(localDataSource.searchTv(title), config).build()
        }
}