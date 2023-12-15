package com.example.examenordinario.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenordinario.domain.GetMoviesUseCase
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {
    var httpCodeMovie: Int = 0
    val movieModel = MutableLiveData<Int>()

    //var getCookbookUseCase = GetCookBookUseCase(idUser)
    val getMoviesUseCase : GetMoviesUseCase by lazy { GetMoviesUseCase() }
    fun onCreate() {
        viewModelScope.launch {
            try {
                val result = getMoviesUseCase()
                httpCodeMovie = result
                movieModel.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}