package com.example.myflicks

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
 * Use the [AddSeriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddSeriesFragment : Fragment() {
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitDataSeries.setOnClickListener{
            val title = titleSeriesInput.text.toString()
            val director = directorSeriesInput.text.toString()
            val description = descriptionSeriesInput.text.toString()
            var seasons : Int? = null

            if (title.isNotEmpty())
            {
                if (releaseSeriesInput.text.toString().isNotEmpty())
                {
                    try
                    {
                        try
                        {
                            seasons = seasonsSeriesInput.text.toString().toInt()
                        }
                        catch (e: Exception)
                        {

                        }
                        val year = releaseSeriesInput.text.toString().toInt()
                        val id = Date().time.toString()
                        val firebaseInput = SeriesRow(id,title,director,year,seasons,description)
                        mainViewModel.databaseReferenceSeries.child(id).setValue(firebaseInput)
                        titleSeriesInput.setText("")
                        directorSeriesInput.setText("")
                        descriptionSeriesInput.setText("")
                        releaseSeriesInput.setText("")
                        seasonsSeriesInput.setText("")

                    }
                    catch (e: Exception)
                    {
                        releaseSeriesInput.error = "Invalid value."
                    }

                }
                else
                {
                    releaseSeriesInput.error = "Release year is reguired."
                }
            }
            else
            {
                titleSeriesInput.error = "Title is required."
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddSeriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSeriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}