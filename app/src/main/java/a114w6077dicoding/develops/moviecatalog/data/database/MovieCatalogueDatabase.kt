package a114w6077dicoding.develops.moviecatalog.data.database

import a114w6077dicoding.develops.moviecatalog.data.entity.MovieEntity
import a114w6077dicoding.develops.moviecatalog.data.entity.TvShowEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieCatalogueDatabase: RoomDatabase() {
    abstract fun movieCatalogueDao() : MovieCatalogueDao

    companion object{
        @Volatile
        private var INSTANCE: MovieCatalogueDatabase? = null

        fun getInstance(context: Context): MovieCatalogueDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieCatalogueDatabase::class.java,
                    "MovieCatalogue.db")
                    .fallbackToDestructiveMigration()
                    .build().apply {
                        INSTANCE = this
                    }
            }
    }
}