package a114w6077dicoding.develops.moviecatalog.ui.favorite

import a114w6077dicoding.develops.moviecatalog.R
import a114w6077dicoding.develops.moviecatalog.databinding.ActivityFavoriteBinding
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionPagerAdapter = FavoriteSectionPagerAdapter(this)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
        }

        TabLayoutMediator(binding.tabs, binding.viewPager){
            tab, position -> tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tvshow
        )
    }
}