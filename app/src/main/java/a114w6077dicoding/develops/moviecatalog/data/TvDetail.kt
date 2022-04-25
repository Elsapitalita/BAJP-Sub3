package a114w6077dicoding.develops.moviecatalog.data

import com.google.gson.annotations.SerializedName

data class TvDetail(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_air_date")
    val first_air_date: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("number_of_episodes")
    val number_of_episode: Int? = null,

    @field:SerializedName("poster_path")
    val poster_path: String? = null,

    @field:SerializedName("original_name")
    val original_name: String? = null,

    @field:SerializedName("vote_average")
    val vote_average: Double? = null
)
