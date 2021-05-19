package com.example.myflicks

import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val dataArray: ArrayList<MovieRow>,  val mainViewModel: MainViewModel): RecyclerView.Adapter<MovieAdapter.MyViewHolderMovie>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolderMovie{
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_one_row,parent,false)
        return MyViewHolderMovie(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolderMovie, position: Int) {
        holder.titleTextView.text = dataArray[holder.adapterPosition].title
        holder.directorTextView.text = dataArray[holder.adapterPosition].director
        holder.releaseTextView.text = dataArray[holder.adapterPosition].releaseYear.toString()
        holder.descriptionTextView.text = dataArray[holder.adapterPosition].description

        holder.deleteButton.setOnClickListener {
            Log.d("XXX:",mainViewModel.databaseReferenceMovies.child(dataArray[holder.adapterPosition].toString()).toString())
            mainViewModel.databaseReferenceMovies.child(dataArray[holder.adapterPosition].id).removeValue()
        }


    }

    inner class MyViewHolderMovie(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView = view.findViewById(R.id.movieTitleTextView) as TextView
        val directorTextView = view.findViewById(R.id.movieDirectorTextView) as TextView
        val releaseTextView = view.findViewById(R.id.movieReleaseTextView) as TextView
        val descriptionTextView = view.findViewById(R.id.movieDescriptionTextView) as TextView
        val deleteButton = view.findViewById(R.id.buttonDeleteMovie) as Button
    }


}