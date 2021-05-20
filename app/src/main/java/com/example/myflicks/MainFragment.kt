package com.example.myflicks

import android.app.Application
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_main.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var moviesFragment: MoviesFragment
    private lateinit var seriesFragment: SeriesFragment
    private lateinit var addMovieFragment: AddMovieFragment
    private lateinit var addSeriesFragment: AddSeriesFragment
    private lateinit var mainViewModel: MainViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )).get(MainViewModel::class.java)
        //jesli jakis uzytkownik jest zalogowany, to pobiera sie jego uid i na jego podstawie tworzona jest referencja do bazy
        if (mainViewModel.fAuth.currentUser != null) {
            mainViewModel.userID = mainViewModel.fAuth.currentUser.uid
            Log.d("MF: ",mainViewModel.fAuth.currentUser.uid)
            mainViewModel.databaseReferenceMovies = mainViewModel.fireBase.getReference(mainViewModel.sbMovie+mainViewModel.userID)
            mainViewModel.databaseReferenceSeries = mainViewModel.fireBase.getReference(mainViewModel.sbSeries+mainViewModel.userID)

        }
        Log.d("Main Fragment","Oncreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        Log.d("Main Fragment","onCreateView")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d("Main Fragment","OnViewCreated")

        //fragmnety widoczne w viewpagerze
        moviesFragment = MoviesFragment()
        seriesFragment = SeriesFragment()
        addMovieFragment = AddMovieFragment()
        addSeriesFragment = AddSeriesFragment()

        tab_layout.setupWithViewPager(viewPager)

        var viewPagerAdapter = ViewPagerAdapter(requireFragmentManager(),1)
        viewPagerAdapter.addFragment(moviesFragment,"Movies")
        viewPagerAdapter.addFragment(seriesFragment,"Series")
        viewPagerAdapter.addFragment(addMovieFragment,"Add movie")
        viewPagerAdapter.addFragment(addSeriesFragment,"Add series")

        viewPager.adapter = viewPagerAdapter


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //dzieki slowie "State" pager nie zapamietuje starych widokow
    //obsluga pagera
    class ViewPagerAdapter(fm : FragmentManager, behavior: Int ) : FragmentStatePagerAdapter(fm,behavior) {
        private var fragments : MutableList<Fragment> = mutableListOf()
        private var fragmentTitles : MutableList<String> = mutableListOf()

        fun addFragment(fragment : Fragment, title : String)
        {
            fragments.add(fragment)
            fragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return  fragments.get(position)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitles.get(position)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
        }

    }

    //reakcja po kliknieciu w przycisk "Log out" w górnym menu - przejscie do widoku logowania oraz wylogowanie uzytkownika w fAuth
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.log_out -> {
                mainViewModel.fAuth.signOut()
                requireView().findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                return true
            }
            else -> false
        }
    }


    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {

        //tworzy sie menu
        //tworzy sie napis z aktualnie zalogowanym uzytkownikiem
        val tv = TextView(context)
        tv.text = mainViewModel.fAuth.currentUser?.email ?: ""
        tv.setPadding(5, 0, 5, 0)
        tv.setTypeface(null, Typeface.BOLD)
        tv.textSize = 14f
        menu.add("userName").setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_menu, menu)
    }


}