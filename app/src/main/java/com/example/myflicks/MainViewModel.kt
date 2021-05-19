package com.example.myflicks

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainViewModel(application: Application):AndroidViewModel(application) {
    val fireBase = FirebaseDatabase.getInstance()
    var sbMovie = "movies"
    var sbSeries = "series"
    var userID = ""
    var databaseReferenceMovies: DatabaseReference = fireBase.getReference(sbMovie+userID)
    var databaseReferenceSeries: DatabaseReference = fireBase.getReference(sbSeries+userID)

    var listOfMovies: ArrayList<MovieRow> = ArrayList()
    var listOfSeries: ArrayList<SeriesRow> = ArrayList()

    var fAuth = FirebaseAuth.getInstance()

}