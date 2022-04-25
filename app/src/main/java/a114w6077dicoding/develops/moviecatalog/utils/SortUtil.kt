package a114w6077dicoding.develops.moviecatalog.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtil {
    const val BEST_VOTE = "Best"
    const val WORST_VOTE = "Worst"
    const val RANDOM = "Random"
    const val MOVIE_ENTITIES = "movie_entities"
    const val TV_SHOW_ENTITIES = "tv_show_entities"

    fun getSortedQuery(
        filter: String,
        table_name: String
    ): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("select * from $table_name")
        when(filter){
            BEST_VOTE -> simpleQuery.append("order by vote_average desc")
            WORST_VOTE -> simpleQuery.append(("order by vote_average asc"))
            RANDOM -> simpleQuery.append("order by RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}