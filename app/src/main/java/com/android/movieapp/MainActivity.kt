package com.android.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.movieapp.presentation.Movies.MoviesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,  MoviesFragment()).commit()
        }
    }
}