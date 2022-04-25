package a114w6077dicoding.develops.moviecatalog.utils

import a114w6077dicoding.develops.moviecatalog.data.model.TvResults
import androidx.recyclerview.widget.DiffUtil

class TvDiffCallback (private val mTvOld: List<TvResults>, private val mTvNew: List<TvResults>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mTvOld.size
    }

    override fun getNewListSize(): Int {
        return mTvNew.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mTvOld[oldItemPosition].original_name == mTvNew[newItemPosition].original_name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployed = mTvOld[oldItemPosition]
        val newEmployed = mTvNew[oldItemPosition]
        return oldEmployed.original_name == newEmployed.original_name && oldEmployed.original_name == newEmployed.original_name
    }
}