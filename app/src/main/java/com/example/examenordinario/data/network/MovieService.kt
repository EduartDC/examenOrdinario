package com.example.examenordinario.data.network

import com.example.examenordinario.core.RetrofitHelper
import com.example.examenordinario.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getHorrorMovies(): Pair<Int, List<Movie>>{
        return withContext(Dispatchers.IO){
            var code : Int = 0
            var body = emptyList<Movie>()
            try{
                val response = retrofit.create(APIClient::class.java).getHorrorMovies()
                if (response.isSuccessful) {
                    code = response.code()
                    body = response.body() ?: emptyList()
                }
                else{
                    code = response.code()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                code = 500
            }
            Pair(code, body)
        }
    }
}