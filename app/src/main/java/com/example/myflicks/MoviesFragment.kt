package com.example.myflicks

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mainViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.d("Movies Fragment","OnCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )).get(MainViewModel::class.java)
        Log.d("Movies Fragment","onCreateView")


        viewManager= LinearLayoutManager(requireContext())


        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mainViewModel.fAuth.addAuthStateListener {
//            if(mainViewModel.fAuth.currentUser == null){
//                this.finish()
//            }
//        }

        Log.d("Movies Fragment","OnViewCreated")

        recyclerViewMovies.layoutManager = viewManager
        setupAdapter(mainViewModel.listOfMovies,mainViewModel)
        mainViewModel.databaseReferenceMovies = mainViewModel.fireBase.getReference(mainViewModel.sbMovie+mainViewModel.userID)
        mainViewModel.databaseReferenceMovies.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                mainViewModel.listOfMovies = ArrayList()
                Log.d("MoviesFragment, ", "onDataChange")
                Log.d("MoviesFragment, ", "mainviwmodel sb movies: " + mainViewModel.sbMovie)


                for (row in datasnapshot.children) {
                    val newRow = row.getValue(MovieRow::class.java)
                    mainViewModel.listOfMovies.add(newRow!!)
                }
                if (recyclerViewMovies != null)
                {
                    setupAdapter(mainViewModel.listOfMovies,mainViewModel)
                }

            }
        })


    }

    private fun setupAdapter(arrayData : ArrayList<MovieRow>, mainViewModel: MainViewModel){
        recyclerViewMovies.adapter = MovieAdapter(arrayData,mainViewModel)
        Log.d("MoviesFragment, ", "setupAdapter")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}