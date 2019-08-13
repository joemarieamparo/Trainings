package com.example.mytunes.api.room

import com.example.mytunes.api.models.Movie
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * LocalRepository handles the data locally.
 * The data is saved locally to enable offline support of the application
 */
class LocalRepository (database: MoviesRoomDatabase) {

    lateinit var movies: List<Movie>

    private val movieDao: MoviesDao by lazy {
        database.movieDao()
    }

    suspend fun insert(movie: Movie) {
        withContext(IO) { movieDao.insert(movie) }
    }

    suspend fun getAllMovies(): List<Movie> {
        withContext(IO) {
            movies = movieDao.allMovies()
        }
        return this.movies
    }
}