package com.mayarafernandes.movieplayer

import android.app.ActionBar
import android.content.pm.ActivityInfo
import android.drm.DrmStore
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.item_player.*

class MovieActivity : AppCompatActivity() {

    private lateinit var exoPlayerView: PlayerView
    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var fullscreenButton: ImageView
    private var fullscreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val intent= intent
        val movie = PlayerMediaModel( // ajeitar isso, view deveria saber desse modelo?? Melhor fazer controller fazer outra busca no repositorio?
            intent.getStringExtra("MOVIE_ID"),
            intent.getStringExtra("MOVIE_TITLE"),
            intent.getStringExtra("MOVIE_DESCRIPTION"),
            intent.getStringExtra("VIDEO_URL"),
            intent.getDoubleExtra("VIDEO_DURATION", 0.0),
            intent.getStringExtra("VIDEO_ID")
            )

        playMovie(movie)
        setView(MovieViewModel( //temp
            movie.title,
            movie.description,
            "",
            movie.id,
            false,
            0
        ))
    }

    private fun playMovie(movie: PlayerMediaModel) {
        val videoUrl = movie.videoUrl
        exoPlayerView = exoplayer_view
        fullscreenButton = exoPlayerView.findViewById(R.id.exo_fullscreen_icon)

        fullscreenButton.setOnClickListener {

            if(fullscreen) {
                fullscreenButton.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_fullscreen_open))
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

                supportActionBar?.show()

                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                fullscreen = false
            }

            else {
                fullscreenButton.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_fullscreen_exit))

                supportActionBar?.hide()

                window.decorView.apply {
                    systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                }

                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                fullscreen = true
                }
            }

        try {
            val bandwidthMeter = DefaultBandwidthMeter.Builder(this).build()
            val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            val videoUri = Uri.parse(videoUrl)
            val dataSourceFactory = DefaultHttpDataSourceFactory("movie_player") //generalizar
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)

            exoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, trackSelector)
            exoPlayerView.player = exoPlayer
            exoPlayerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT // testar
            exoPlayer.prepare(mediaSource)
            exoPlayer.playWhenReady = true

        } catch (e: Exception) {
            Log.e("MoviePlayer", "exoplayer error$e")
        }
    }

    fun setView(movie: MovieViewModel) {
        movie_title_text.text = movie.title
        movie_description_text.text = movie.description
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.playWhenReady = false
    }

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }
}
