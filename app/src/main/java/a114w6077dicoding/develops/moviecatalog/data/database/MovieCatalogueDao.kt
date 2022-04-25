package a114w6077dicoding.develops.moviecatalog.data.database

import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface MovieCatalogueDao {

    @Query("SELECT * FROM  movie")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM  movie WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun searchMovie(title: String): DataSource.Factory<Int, MovieEntity>

    @Update
    fun updateMovies(movie: MovieEntity)

    @Query("SELECT * FROM tvShow")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvShow WHERE favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tvShow WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tv: List<TvShowEntity>)

    @Update
    fun updateTvShow(tv: TvShowEntity)

    @Query("SELECT * FROM tvShow WHERE original_name LIKE '%' || :title || '%'")
    fun searchTvShow(title: String): DataSource.Factory<Int, TvShowEntity>

}