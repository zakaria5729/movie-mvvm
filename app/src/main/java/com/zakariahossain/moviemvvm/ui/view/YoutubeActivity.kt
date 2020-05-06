package com.zakariahossain.moviemvvm.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.zakariahossain.moviemvvm.R
import com.zakariahossain.moviemvvm.util.Constants.RECOVERY_REQUEST
import com.zakariahossain.moviemvvm.util.Constants.VIDEO_NAME
import com.zakariahossain.moviemvvm.util.Constants.VIDEO_PATH
import com.zakariahossain.moviemvvm.util.Constants.YOUTUBE_API_KEY
import com.zakariahossain.moviemvvm.util.toastShort
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        title = intent.getStringExtra(VIDEO_NAME)
    }

    override fun onResume() {
        super.onResume()
        getYouTubePlayerProvider().initialize(YOUTUBE_API_KEY, this); //Initialize youtube player with api
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean) {
        if(!wasRestored) {
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            youTubePlayer.cueVideo(intent.getStringExtra(VIDEO_PATH)); //play video
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, errorReason: YouTubeInitializationResult) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            val error: String = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_REQUEST && resultCode == RESULT_OK) {
            getYouTubePlayerProvider().initialize(YOUTUBE_API_KEY, this); // Retry initialization if user performed a recovery action
        }
    }

    private fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, this)
        return youTubePlayerView
    }
}