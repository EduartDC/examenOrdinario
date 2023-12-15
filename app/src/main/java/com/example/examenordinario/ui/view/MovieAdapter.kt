package com.example.examenordinario.ui.view

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenordinario.R
import com.example.examenordinario.data.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter( private val onCardClickListener: MovieAdapter.OnCardClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movies: List<Movie> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val imageUri = movie.posterURL
        holder.itemView.setOnClickListener {
            onCardClickListener.onCardClick(position)
        }

        AsyncTask.execute {
            try {
                val bitmap = Picasso.get().load(imageUri).get()
                holder.image.post {
                    holder.image.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun setData(newRecipes: List<Movie>) {
        movies = newRecipes

        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.ivMovie)

    }
    interface OnCardClickListener {
        fun onCardClick(position: Int)
    }
}