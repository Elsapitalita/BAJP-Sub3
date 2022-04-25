package a114w6077dicoding.develops.moviecatalog.ui.detail

import a114w6077dicoding.develops.moviecatalog.R
import a114w6077dicoding.develops.moviecatalog.ViewModelFactory
import a114w6077dicoding.develops.moviecatalog.databinding.ActivityTvDetailBinding
import a114w6077dicoding.develops.moviecatalog.ui.tv.TvShowViewModel
import a114w6077dicoding.develops.moviecatalog.utils.Constanta
import a114w6077dicoding.develops.moviecatalog.vo.Status
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class TvDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: TvShowViewModel
    private lateinit var binding: ActivityTvDetailBinding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataId = intent.getIntExtra(EXTRA_DATA_ID, 0)
        supportActionBar?.title = intent.getStringExtra(EXTRA_TITLE)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        binding.progressBar.visibility = View.VISIBLE
        viewModel.setTvShowId(dataId)

        viewModel.getTvShowDetail.observe(this, { tv ->
            binding.apply {
                if (tv != null) {
                    when (tv.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            tvItemTitle.text = tv.data?.original_name
                            tvPopularity.text = tv.data?.popularity.toString()
                            tvItemScore.text = tv.data?.vote_average.toString()
                            tvItemReleaseDate.text = tv.data?.first_air_date
                            tvItemOverview.text = tv.data?.overview
                            Glide.with(this@TvDetailActivity)
                                .load(Constanta.IMAGE + tv.data?.poster_path)
                                .apply(
                                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error))
                                .into(imagePoster)
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(root.context, "Something Wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        this.menu = menu
        viewModel.getTvShowDetail.observe(this, {
            tv -> binding.apply {
            if (tv != null) {
                when (tv.status) {
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        val state = tv.data?.favorite
                        setFavorite(state)
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(root.context, "Something Wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {

        R.id.action_share -> {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody =
                "This Film ${intent.getStringExtra(EXTRA_TITLE)} is Awesome and Great!!"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Movie Catalogue")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Share With"))
                true
        }

        R.id.action_favorite ->{
            viewModel.setFavorite()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)

        }
    }

    private fun setFavorite(favorite: Boolean?){
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (favorite == true){
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        var EXTRA_DATA_ID = "dataId"
        var EXTRA_TITLE = "extra_title"
    }
}