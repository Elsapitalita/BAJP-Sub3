package a114w6077dicoding.develops.moviecatalog.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoriteSectionPagerAdapter(favoriteActivity: FavoriteActivity) : FragmentStateAdapter(favoriteActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvFragment()
            else -> Fragment()
        }



}
