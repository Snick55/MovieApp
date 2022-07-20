package com.android.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.android.movieapp.presentation.favorite.FavoriteFragment
import com.android.movieapp.presentation.movies.MoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val botNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.movies -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,  MoviesFragment()).commit()

                R.id.favorites -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,  FavoriteFragment()).commit()
                else -> throw IllegalStateException()
            }
            return@setOnItemSelectedListener true

        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MoviesFragment()).commit()
        }
    }





    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }


    fun changeTitle(title: String) {
        toolbar.title = title
    }
}