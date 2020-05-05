package com.zakariahossain.moviemvvm.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zakariahossain.moviemvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickButton(view: View) {
        val intent = Intent(this, SingleMovieActivity::class.java)
        intent.putExtra("movie_id", 181812)
        startActivity(intent)
    }
}
