package com.example.examenordinario.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.examenordinario.R
import com.example.examenordinario.data.model.MovieProvider
import com.example.examenordinario.databinding.ActivityMainBinding
import com.example.examenordinario.databinding.ActivityViewDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewDetails : AppCompatActivity() {
    lateinit var binding: ActivityViewDetailsBinding
    private lateinit var movieAdapter: MovieAdapter
    var idMovie = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentview = intent

        // Verifica si el intent contiene la clave "key_idMovie"
        if (intentview.hasExtra("key_idMovie")) {
            // Obtiene el valor asociado con la clave "key_idMovie"
            idMovie = intentview.getStringExtra("key_idMovie").toString()
        }else{
            finish()
        }
        for (item in MovieProvider.movies){
            if(item.id.toString() == idMovie){

                CoroutineScope(Dispatchers.IO).launch {
                    try {

                        val bitmap = Glide.with(this@ViewDetails)
                            .asBitmap()
                            .load(item.posterURL)
                            .submit().get()

                        withContext(Dispatchers.Main) {
                            binding.ivMovieImage.setImageBitmap(bitmap)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                binding.tvMovieName.text = item.title
            }
        }
    }
}