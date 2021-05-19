package com.example.myflicks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.GridLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.FragmentPagerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

//
//
//    private lateinit var viewPager : ViewPager
//    private lateinit var tabLayout : TabLayout
//    private lateinit var moviesFragment: MoviesFragment
//    private lateinit var seriesFragment: SeriesFragment
//    private lateinit var addMovieFragment: AddMovieFragment
//    private lateinit var addSeriesFragment: AddSeriesFragment
//    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewPager = findViewById(R.id.viewPager)
//        tabLayout = findViewById(R.id.tab_layout)
//
//        moviesFragment = MoviesFragment()
//        seriesFragment = SeriesFragment()
//        addMovieFragment = AddMovieFragment()
//        addSeriesFragment = AddSeriesFragment()
//
//        tabLayout.setupWithViewPager(viewPager)
//
//        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,0)
//        viewPagerAdapter.addFragment(moviesFragment,"Movies")
//        viewPagerAdapter.addFragment(seriesFragment,"Series")
//        viewPagerAdapter.addFragment(addMovieFragment,"Add movie")
//        viewPagerAdapter.addFragment(addSeriesFragment,"Add series")
//
//        viewPager.adapter = viewPagerAdapter


    }


//
//    class ViewPagerAdapter(fm : FragmentManager, behavior: Int ) : FragmentPagerAdapter(fm,behavior) {
//        private var fragments : MutableList<Fragment> = mutableListOf()
//        private var fragmentTitles : MutableList<String> = mutableListOf()
//
//        fun addFragment(fragment : Fragment, title : String)
//        {
//            fragments.add(fragment)
//            fragmentTitles.add(title)
//        }
//
//        override fun getItem(position: Int): Fragment {
//            return  fragments.get(position)
//        }
//
//        override fun getCount(): Int {
//            return fragments.size
//        }
//
//        override fun getPageTitle(position: Int): CharSequence? {
//            return fragmentTitles.get(position)
//        }
//
//    }


}