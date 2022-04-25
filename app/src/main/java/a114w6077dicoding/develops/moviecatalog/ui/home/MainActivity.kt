package a114w6077dicoding.develops.moviecatalog.ui.home

import a114w6077dicoding.develops.moviecatalog.R
import a114w6077dicoding.develops.moviecatalog.databinding.ActivityMainBinding
import a114w6077dicoding.develops.moviecatalog.ui.favorite.FavoriteActivity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = SectionPagerAdapter(this)
        binding.apply {
            view_pager.adapter = viewPagerAdapter
        }

        TabLayoutMediator(binding.tabLayout2, binding.viewPager) { tab, position ->
            tab.text = resources.getString(tabTitle[position])
        }.attach()

        binding.fabFavorite.setOnClickListener{
         val intent = Intent(this, FavoriteActivity::class.java)
         startActivity(intent)
        }

    }

    companion object {
        @StringRes
        private val tabTitle = intArrayOf(R.string.movie, R.string.tvshow)}


}