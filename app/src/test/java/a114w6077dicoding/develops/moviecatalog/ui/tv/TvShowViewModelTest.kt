package a114w6077dicoding.develops.moviecatalog.ui.tv

import a114w6077dicoding.develops.moviecatalog.data.MovieCatalogueRepository
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private val tvDummy = DataDummy.getDummyTv()[0]
    private val tvId = tvDummy?.id as Int

    @Mock
    private var movieCatalogueRepository = mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<TvShowEntity>>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieCatalogueRepository)
        viewModel.setTvShowId(tvId)
    }

    @Test
    fun getTvShow(){
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tv.value = dummyTv

        `when`(movieCatalogueRepository.getTvShows()).thenReturn(tv)
        val tvData = viewModel.getTvShow().value?.data
        verify(movieCatalogueRepository).getTvShows()
        assertNotNull(tvData)
        assertEquals(dummyTv.data?.size, tvData?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun nullTvShow() {
        `when`(movieCatalogueRepository.getTvShows()).thenReturn(null)
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getTvShow().value
        }
        assertEquals(null, exception.message)
    }

    @Test
    fun getDetailTvShow(){
        val tv = MutableLiveData<Resource<TvShowEntity>>()
        val dummyTv = Resource.success(DataDummy.getDummyTvDetail())
        tv.value = dummyTv

        `when`(movieCatalogueRepository.getDetailTvShow(tvId)).thenReturn(tv)
        val observe = mock(Observer::class.java) as Observer<Resource<TvShowEntity>>
        viewModel.getTvShowDetail.observeForever(observe)
        verify(observe).onChanged(dummyTv)
    }

    @Test
    fun getFavoriteTv() {
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(10)
        val tvs = MutableLiveData<PagedList<TvShowEntity>>()
        tvs.value = dummyTv

        `when`(movieCatalogueRepository.getFavoriteTv()).thenReturn(tvs)
        val tvsEntities = viewModel.getTvShowFavorite().value
        com.nhaarman.mockitokotlin2.verify(movieCatalogueRepository).getFavoriteTv()
        assertNotNull(tvsEntities)
        assertEquals(10, tvsEntities?.size)

        viewModel.getTvShowFavorite().observeForever(observerFavorite)
        com.nhaarman.mockitokotlin2.verify(observerFavorite).onChanged(dummyTv)
    }

    @Test
    fun nullFavorite(){
        `when`(movieCatalogueRepository.getFavoriteTv()).thenReturn(null)
        val exception = assertThrows(java.lang.NullPointerException::class.java) {
            viewModel.getTvShowFavorite().value
        }
        assertEquals(null, exception.message)
    }

    @Test
    fun searchTv(){
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<TvShowEntity>>()
        tv.value = dummyTv

        `when`(movieCatalogueRepository.searchTv("Days")).thenReturn(tv)
        val tvData = viewModel.searchTvShow("Days").value
        verify(movieCatalogueRepository).searchTv("Days")
        assertNotNull(tvData)
        assertEquals(dummyTv.size, tvData?.size)

        viewModel.searchTvShow("Days").observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyTv)
    }

}