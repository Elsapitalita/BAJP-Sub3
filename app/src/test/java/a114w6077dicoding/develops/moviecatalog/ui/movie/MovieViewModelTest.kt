package a114w6077dicoding.develops.moviecatalog.ui.movie


import a114w6077dicoding.develops.moviecatalog.data.MovieCatalogueRepository
import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.utils.DataDummy
import a114w6077dicoding.develops.moviecatalog.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{

    private lateinit var viewModel: MovieViewModel
    private val movieDummy = DataDummy.getDummyMovies()[0]
    private val movieId = movieDummy.id as Int


    @Mock
    private var movieCatalogueRepository = mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<MovieEntity>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieCatalogueRepository)
        viewModel.setMovieId(movieId)
    }

    @Test
    fun getMovie(){
        val movieDataDummy = Resource.success(pagedList)
        `when`(movieDataDummy.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = movieDataDummy

        `when`(movieCatalogueRepository.getMovies()).thenReturn(movie)
        val movieData = viewModel.getMovie().value?.data
        verify(movieCatalogueRepository).getMovies()
        assertNotNull(movieData)
        assertEquals(movieDataDummy.data?.size, movieData?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(movieDataDummy)
    }

    @Test
    fun nullMovie() {
        val movieDataDummy = Resource.success(DataDummy.getDummyMovies())
        val movie = MutableLiveData<Resource<List<MovieEntity>>>()
        movie.value = movieDataDummy

        `when`(movieCatalogueRepository.getMovies()).thenReturn(null)
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getMovie().value?.data
        }
        verify(movieCatalogueRepository).getMovies()
        assertEquals(null, exception.message)
    }


    @Test
    fun getDetailMovie(){
        val dummyMovie = Resource.success(DataDummy.getDummyMoviesDetail())
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieCatalogueRepository.getDetailMovie(movieId)).thenReturn(movies)
        val observe = mock(Observer::class.java) as Observer<Resource<MovieEntity>>
        viewModel.getMovieDetail.observeForever(observe)
        verify(observe).onChanged(dummyMovie)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyFavoriteMovie = pagedList
        `when`(dummyFavoriteMovie.size).thenReturn(10)
        val favoriteMovie = MutableLiveData<PagedList<MovieEntity>>()
        favoriteMovie.value = dummyFavoriteMovie

        `when`(movieCatalogueRepository.getFavoriteMovie()).thenReturn(favoriteMovie)
        val movieEntities = viewModel.getMovieFavorite().value
        verify(movieCatalogueRepository).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovieFavorite().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyFavoriteMovie)
    }

    @Test
    fun nullFavorite(){
        `when`(movieCatalogueRepository.getFavoriteMovie()).thenReturn(null)
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getMovieFavorite().value
        }
        assertEquals(null, exception.message)
    }

    @Test
    fun searchMovie(){
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(movieCatalogueRepository.searchMovie("Days")).thenReturn(movie)
        val tvData = viewModel.searchMovie("Days").value
        verify(movieCatalogueRepository).searchMovie("Days")
        assertNotNull(tvData)
        assertEquals(dummyMovie.size, tvData?.size)

        viewModel.searchMovie("Days").observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyMovie)
    }

}