package com.example.examenordinario.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenordinario.R
import com.example.examenordinario.data.model.MovieProvider
import com.example.examenordinario.databinding.ActivityMainBinding
import com.example.examenordinario.ui.viewModel.MoviesViewModel

class MainActivity : AppCompatActivity(), MovieAdapter.OnCardClickListener {
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MoviesViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //llama al viewmodel
        movieViewModel.onCreate()
        //condiguracion de recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        //inicializa el adaptador
        movieAdapter = MovieAdapter(this)
        binding.recyclerview.adapter = movieAdapter
        movieViewModel.movieModel.observe(this, {
            if(movieViewModel.httpCodeMovie == 200){
                movieAdapter.setData(MovieProvider.movies)
            }
        })
    }

    override fun onCardClick(position: Int) {

        val idMovie = MovieProvider.movies[position].id
        // Crea un Intent
        val intent = Intent(this, ViewDetails::class.java)

        // Agrega el String al Intent como un extra
        intent.putExtra("key_idMovie", idMovie.toString())

        // Inicia la nueva Activity
        startActivity(intent)
    }
}