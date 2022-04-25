package a114w6077dicoding.develops.moviecatalog.utils

import a114w6077dicoding.develops.moviecatalog.data.model.MovieResults
import androidx.recyclerview.widget.DiffUtil

class MovieDiffCallback (private val mMovieOld: List<MovieResults>, private val mMovieNew: List<MovieResults>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mMovieOld.size
    }

    override fun getNewListSize(): Int {
        return mMovieNew.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mMovieOld[oldItemPosition].title == mMovieNew[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployed = mMovieOld[oldItemPosition]
        val newEmployed = mMovieNew[newItemPosition]
        return oldEmployed.title == newEmployed.title && oldEmployed.title == newEmployed.title
    }

}