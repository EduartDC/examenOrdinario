package com.example.examenordinario.data.network

import com.example.examenordinario.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface APIClient {
    @GET("movies/horror")
    suspend fun getHorrorMovies(): Response<List<Movie>>
}