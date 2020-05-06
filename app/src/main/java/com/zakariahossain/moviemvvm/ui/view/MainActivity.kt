package com.zakariahossain.moviemvvm.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zakariahossain.moviemvvm.R
import com.zakariahossain.moviemvvm.util.Constants.MOVIE_ID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickButton(view: View) {
        val intent = Intent(this, SingleMovieActivity::class.java)
        intent.putExtra(MOVIE_ID, 338762)
        startActivity(intent)
    }
}
