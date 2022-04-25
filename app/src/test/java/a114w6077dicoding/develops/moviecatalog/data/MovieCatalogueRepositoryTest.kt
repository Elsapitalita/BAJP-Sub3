package a114w6077dicoding.develops.moviecatalog.data

import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import a114w6077dicoding.develops.moviecatalog.data.source.remote.RemoteDataSource
import a114w6077dicoding.develops.moviecatalog.ui.tv.PagedListUtil
import a114w6077dicoding.develops.moviecatalog.utils.AppExecutors
import a114w6077dicoding.develops.moviecatalog.utils.DataDummy
import a114w6077dicoding.develops.moviecatalog.utils.LiveDataTestUtil
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    
    private val movieTvRepository = DummyMovieCatalogueRepository(remote, local, appExecutors)

    private val movieDummyData = DataDummy.getDummyMovies()
    private val movieId = movieDummyData?.get(0)?.id as Int

    private val tvDummyData = DataDummy.getDummyTv()
    private val tvId = tvDummyData?.get(0)?.id as Int

    private val dummyMovieDetail = DataDummy.getDummyMoviesDetail()
    private val dummyTvDetail = DataDummy.getDummyTvDetail()

    @Test
    fun getListMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        movieTvRepository.getMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyMovies()))
        verify(local).getMovies()
        Assert.assertNotNull(movieEntity.data)
        assertEquals(movieDummyData.size, movieEntity.data?.size)
    }

    @Test
    fun getMovieDetails(){
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = dummyMovieDetail
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieTvRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        Assert.assertNotNull(movieEntity)
        assertEquals(dummyMovieDetail.id, movieEntity.data?.id)
        assertEquals(dummyMovieDetail.title, movieEntity.data?.title)
        assertEquals(dummyMovieDetail.poster_path, movieEntity.data?.poster_path)
        assertEquals(dummyMovieDetail.overview, movieEntity.data?.overview)
        assertEquals(dummyMovieDetail.release_date, movieEntity.data?.release_date)
        assertEquals(dummyMovieDetail.vote_average, movieEntity.data?.vote_average)
    }

    @Test
    fun getTvShowList(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShow()).thenReturn(dataSourceFactory)
        movieTvRepository.getTvShows()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTv()))
        verify(local).getTvShow()
        Assert.assertNotNull(tvEntities)
        assertEquals(tvDummyData.size, tvEntities.data?.size)
    }

    @Test
    fun getTvDetails(){
        val tvEntity = MutableLiveData<TvShowEntity>()
        tvEntity.value = dummyTvDetail
        `when`(local.getDetailTv(tvId)).thenReturn(tvEntity)

        val tvDetails = LiveDataTestUtil.getValue(movieTvRepository.getDetailTvShow(tvId))
        verify(local).getDetailTv(tvId)
        Assert.assertNotNull(tvDetails)
        assertEquals(dummyTvDetail.id, tvDetails.data?.id)
        assertEquals(dummyTvDetail.original_name, tvDetails.data?.original_name)
        assertEquals(dummyTvDetail.overview, tvDetails.data?.overview)
        assertEquals(dummyTvDetail.poster_path, tvDetails.data?.poster_path)
        assertEquals(dummyTvDetail.first_air_date, tvDetails.data?.first_air_date)
    }

    @Test
    fun getMovieFavorite(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieTvRepository.getFavoriteMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyMovies()))
        verify(local).getFavoriteMovie()
        Assert.assertNotNull(movieEntities)
        assertEquals(movieEntities.data?.size, movieDummyData.size)
    }

    @Test
    fun getTvFavorite(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllFavoriteTv()).thenReturn(dataSourceFactory)
        movieTvRepository.getFavoriteTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTv()))
        verify(local).getAllFavoriteTv()
        Assert.assertNotNull(tvEntities)
        assertEquals(tvEntities.data?.size, tvDummyData.size)
    }

    @Test
    fun searchMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.searchMovie("venom")).thenReturn(dataSourceFactory)
        movieTvRepository.searchMovie("venom")

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTv()))
        verify(local).searchMovie("venom")
        Assert.assertNotNull(tvEntities)
        assertEquals(tvEntities.data?.size, movieDummyData.size)
    }

    @Test
    fun searchTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.searchTv("Days")).thenReturn(dataSourceFactory)
        movieTvRepository.searchTv("Days")

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTv()))
        verify(local).searchTv("Days")
        Assert.assertNotNull(tvEntities)
        assertEquals(tvEntities.data?.size, tvDummyData.size)
    }
}