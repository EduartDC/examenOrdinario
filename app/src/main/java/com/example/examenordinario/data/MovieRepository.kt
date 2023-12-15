package com.example.examenordinario.data

import com.example.examenordinario.data.model.MovieProvider
import com.example.examenordinario.data.network.MovieService

class MovieRepository {
    private val movieService = MovieService()
    suspend fun getMovies(): Int{
        var response = movieService.getHorrorMovies()
        MovieProvider.movies = response.second.toMutableList()
        return response.first
    }
}