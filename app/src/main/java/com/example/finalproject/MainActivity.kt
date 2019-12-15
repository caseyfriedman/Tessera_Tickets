package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.finalproject.fragments.AllPassesActivity
import com.example.finalproject.fragments.BuyPassActivity
import com.example.finalproject.fragments.MainFragment
import com.example.finalproject.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var startedBefore = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            var startedBefore = false
        }



        nav_view.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener)

        showFragmentByTag(AllPassesActivity.TAG, false)


    }

    public fun showFragmentByTag(
        tag: String,
        toBackStack: Boolean
    ) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            when (tag) {
                AllPassesActivity.TAG -> {
                    fragment =
                        AllPassesActivity()
                }
                BuyPassActivity.TAG -> {
                    fragment =
                        BuyPassActivity()
                }
                MainFragment.TAG -> {
                    fragment = MainFragment()
                }
                SearchFragment.TAG -> {
                    fragment = SearchFragment()
                }
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager
                .beginTransaction()
            ft.replace(R.id.fragmentContainer, fragment!!, tag)
            if (toBackStack) {
                ft.addToBackStack(null)
            }
            ft.commit()
        }
    }

    private val myOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->


            if (!startedBefore) {
                showFragmentByTag(MainFragment.TAG, true) // need the main fragment
                startedBefore = true // I only want this if statement executed once
                return@OnNavigationItemSelectedListener true

            }

            when (item.itemId) {
                R.id.navigation_home -> {
                    showFragmentByTag(MainFragment.TAG, true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    showFragmentByTag(SearchFragment.TAG, true)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_all_passes -> {
                    showFragmentByTag(AllPassesActivity.TAG, true)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }


}


