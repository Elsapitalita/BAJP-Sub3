package a114w6077dicoding.develops.moviecatalog.data.model

import com.google.gson.annotations.SerializedName

data class Tv(
    @field:SerializedName("results")
    val results: List<TvResults>? = null
)

data class TvResults(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_air_date")
    val first_air_date: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val poster_path: String? = null,

    @field:SerializedName("original_name")
    val original_name: String? = null,

    @field:SerializedName("vote_average")
    val vote_average: Double? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

)