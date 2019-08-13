package com.example.mytunes.api.room

import android.content.Context
import androidx.room.*
import com.example.mytunes.api.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao

    companion object {
        @Volatile
        var INSTANCE: MoviesRoomDatabase? = null

        fun getDatabase(context: Context): MoviesRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(MoviesRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MoviesRoomDatabase::class.java, "movies_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}

@Dao
interface MoviesDao {

    @Query("SELECT * from movies_table ORDER BY id ASC")
    fun allMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("DELETE FROM movies_table")
    fun deleteAll()
}