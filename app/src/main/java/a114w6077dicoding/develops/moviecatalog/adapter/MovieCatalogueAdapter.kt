package a114w6077dicoding.develops.moviecatalog.adapter

import a114w6077dicoding.develops.moviecatalog.R
import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.databinding.ItemListBinding
import a114w6077dicoding.develops.moviecatalog.ui.detail.MovieDetailActivity
import a114w6077dicoding.develops.moviecatalog.utils.Constanta
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieCatalogueAdapter : PagedListAdapter<MovieEntity,MovieCatalogueAdapter.MovieViewHolder>(DIFF_CALLBACK) {


    class MovieViewHolder(var binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.binding.apply {
                tvItemTitle.text = movie.title
                tvItemReleasedDate.text = movie.release_date
                Glide.with(holder.itemView.context)
                    .load(Constanta.IMAGE + movie.poster_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imagePoster)
            }
            holder.itemView.setOnClickListener{
                val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.EXTRA_DATA_ID, movie.id)
                intent.putExtra(MovieDetailActivity.EXTRA_TITLE, movie.title)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}