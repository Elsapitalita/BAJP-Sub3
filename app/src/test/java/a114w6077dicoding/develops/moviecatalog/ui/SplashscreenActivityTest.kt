package a114w6077dicoding.develops.moviecatalog.ui

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class SplashscreenActivityTest{

    private lateinit var activity: SplashscreenActivity

    @Before
    fun setUp(){
      activity = Robolectric.buildActivity(SplashscreenActivity::class.java)
          .create().resume().get()
    }

    @Test
    fun testFinishing(){
        assertFalse(activity.isFinishing)
        activity.finish()
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        assertTrue(activity.isFinishing)
    }

    @Test
    fun cekActivityNotNull(){
        assertNotNull(activity)
    }

}