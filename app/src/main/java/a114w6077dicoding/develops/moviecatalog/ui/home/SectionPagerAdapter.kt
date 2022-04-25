package a114w6077dicoding.develops.moviecatalog.ui.home

import a114w6077dicoding.develops.moviecatalog.ui.movie.MovieFragment
import a114w6077dicoding.develops.moviecatalog.ui.tv.TvShowFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(fragmentLis: FragmentActivity) : FragmentStateAdapter(fragmentLis) {

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment = when(position){
        0 -> MovieFragment()
        1 -> TvShowFragment()
        else -> Fragment()
    }
}