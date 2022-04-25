package a114w6077dicoding.develops.moviecatalog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    var id: Int? = null,
    var overview: String? = null,
    var title: String? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var vote_average: Double? = null,
    var popularity: Double? = null,
    var favorite: Boolean = false,
    var status: String? = null
)