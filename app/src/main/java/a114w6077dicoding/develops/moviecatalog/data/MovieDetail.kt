package a114w6077dicoding.develops.moviecatalog.data

import com.google.gson.annotations.SerializedName

data class MovieDetail (
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("runtime")
    val runtime: Int? = null,

    @field:SerializedName("poster_path")
    val poster_path: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val vote_average: Double? = null
)


