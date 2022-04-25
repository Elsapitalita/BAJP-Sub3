package a114w6077dicoding.develops.moviecatalog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowEntity(
    @PrimaryKey
    var id: Int? = null,
    var overview: String? = null,
    var original_name: String? = null,
    var poster_path: String? = null,
    var first_air_date: String? = null,
    var vote_average: Double?= null,
    var popularity: Double? = null,
    var favorite: Boolean = false
)
