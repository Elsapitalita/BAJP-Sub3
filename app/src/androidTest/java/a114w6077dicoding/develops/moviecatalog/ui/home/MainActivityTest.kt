package a114w6077dicoding.develops.moviecatalog.ui.home

import a114w6077dicoding.develops.moviecatalog.R
import a114w6077dicoding.develops.moviecatalog.utils.DataDummy
import a114w6077dicoding.develops.moviecatalog.utils.EspressoIdlingResource
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test



class MainActivityTest{

    private val movieDummy = DataDummy.getDummyMovies()
    private val tvShowDummy = DataDummy.getDummyTv()


    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.IdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.IdlingResource)
    }

    @Test
    fun loadMovie(){
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(movieDummy.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it.size)
        })
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_itemPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.action_share)).check(matches(isDisplayed()))
        onView(withId(R.id.action_share)).perform(click())

    }

    @Test
    fun loadTVShow(){
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(tvShowDummy.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it.size)
        })
    }

    @Test
    fun loadDetailTVShow(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_Popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.action_share)).check(matches(isDisplayed()))
        onView(withId(R.id.action_share)).perform(click())
    }

    @Test
    fun makeAndDeleteMovieFavorite(){
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_ItemTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_itemPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(movieDummy.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it.size)
        })
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavoriteMovie(){
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(movieDummy.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it.size)
        })
    }

    @Test
    fun makeAndDeleteTvFavorite(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_ItemTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ItemOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_Popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(tvShowDummy.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it.size)
        })
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

    }

    @Test
    fun loadFavoriteTv(){
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(tvShowDummy.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it.size)
        })
    }

}
