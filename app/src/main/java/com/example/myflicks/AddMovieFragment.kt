package com.example.myflicks

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_movie.*
import kotlinx.android.synthetic.main.fragment_add_series.*
import java.lang.Exception
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d("AddMovies Fragment","OnCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        //instancja viewmodelu
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )).get(MainViewModel::class.java)

        Log.d("AddMovies Fragment","onCreateView")

        return inflater.inflate(R.layout.fragment_add_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reakcja na przycisk zatwierdzajacy dane
        submitDataMovie.setOnClickListener{
            val title = movieTitleInput.text.toString()
            val director = directorMovieInput.text.toString()
            val description = descriptionMovieInput.text.toString()
            if (title.isNotEmpty())
            {
                if (releaseMovieInput.text.toString().isNotEmpty())
                {
                    try
                    {
                        val year = releaseMovieInput.text.toString().toInt()
                        val id = Date().time.toString()
                        //dane są dodawane do bazy danych w firebase'ie
                        val firebaseInput = MovieRow(id,title,director,year,description)
                        mainViewModel.databaseReferenceMovies.child(id).setValue(firebaseInput)
                        //czyszczenie pol
                        movieTitleInput.setText("")
                        directorMovieInput.setText("")
                        descriptionMovieInput.setText("")
                        releaseMovieInput.setText("")

                    }
                    catch (e: Exception)
                    {
                        releaseMovieInput.error = "Invalid value."
                    }

                }
                else
                {
                    releaseMovieInput.error = "Release year is reguired."
                }
            }
            else
            {
                movieTitleInput.error = "Title is required."
            }
        }

        Log.d("AddMovies Fragment","OnViewCreated")

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}