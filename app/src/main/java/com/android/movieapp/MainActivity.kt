package com.android.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.movieapp.presentation.movies.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)


        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,  MoviesFragment()).commit()
        }
    }

    fun changeTitle (title: String){
        toolbar.title = title
    }
}