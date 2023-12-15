package com.example.examenordinario.domain

import com.example.examenordinario.data.MovieRepository

class GetMoviesUseCase {
    private val repository = MovieRepository()

    suspend operator fun invoke(): Int = repository.getMovies()
}