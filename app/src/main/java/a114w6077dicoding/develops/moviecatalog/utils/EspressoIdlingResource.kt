@file:Suppress("MemberVisibilityCanBePrivate")

package a114w6077dicoding.develops.moviecatalog.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE: String = "GLOBAL"

    val IdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        IdlingResource.increment()
    }
    fun decrement(){
        IdlingResource.decrement()
    }

}