package com.example.myflicks

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainViewModel(application: Application):AndroidViewModel(application) {
    val fireBase = FirebaseDatabase.getInstance()
    //skladowe nazwy bazy danych tworzonej dla kazdego uzytkownika
    var sbMovie = "movies"
    var sbSeries = "series"
    //wartosc aktualizuje sie, gdy uzytkownik zaloguje sie
    var userID = ""
    var databaseReferenceMovies: DatabaseReference = fireBase.getReference(sbMovie+userID)
    var databaseReferenceSeries: DatabaseReference = fireBase.getReference(sbSeries+userID)

    //listy wyswietlane w "movies" i "series"
    var listOfMovies: ArrayList<MovieRow> = ArrayList()
    var listOfSeries: ArrayList<SeriesRow> = ArrayList()

    //instancja pozwalajaca na obsluge uzytkonika
    var fAuth = FirebaseAuth.getInstance()

}