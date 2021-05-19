package com.example.myflicks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SeriesAdapter(private val dataArray: ArrayList<SeriesRow>, val mainViewModel: MainViewModel): RecyclerView.Adapter<SeriesAdapter.MyViewHolderSeries>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolderSeries{
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.series_one_row,parent,false)
        return MyViewHolderSeries(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolderSeries, position: Int) {
        holder.titleTextView.text = dataArray[holder.adapterPosition].title
        holder.directorTextView.text = dataArray[holder.adapterPosition].director
        holder.releaseTextView.text = dataArray[holder.adapterPosition].releaseYear.toString()
        if(dataArray[holder.adapterPosition].numberOfSeasons.toString() != "null" )
        {
            holder.seasonsTextView.text = dataArray[holder.adapterPosition].numberOfSeasons.toString()
        }
        else
        {
            holder.seasonsTextView.text = ""
        }
        holder.descriptionTextView.text = dataArray[holder.adapterPosition].description

        holder.deleteButton.setOnClickListener {
            mainViewModel.databaseReferenceSeries.child(dataArray[holder.position].id).removeValue()
        }

    }

    inner class MyViewHolderSeries(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView = view.findViewById(R.id.seriesTitleTextView) as TextView
        val directorTextView = view.findViewById(R.id.seriesDirectorTextView) as TextView
        val releaseTextView = view.findViewById(R.id.seriesReleaseTextView) as TextView
        val seasonsTextView = view.findViewById(R.id.seriesSeasonsTextView) as TextView
        val descriptionTextView = view.findViewById(R.id.seriesDescriptionTextView) as TextView
        val deleteButton = view.findViewById(R.id.buttonDeleteSeries) as Button
    }


}