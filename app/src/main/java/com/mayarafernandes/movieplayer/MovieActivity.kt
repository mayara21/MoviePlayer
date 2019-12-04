package com.mayarafernandes.movieplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.item_player.*

class MovieActivity : AppCompatActivity() {

    private lateinit var exoPlayerView: PlayerView
    private lateinit var exoPlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val intent= intent
        val movie = PlayerMediaModel( // ajeitar isso
            intent.getStringExtra("MOVIE_ID"),
            intent.getStringExtra("MOVIE_TITLE"),
            intent.getStringExtra("MOVIE_DESCRIPTION"),
            intent.getStringExtra("VIDEO_URL"),
            intent.getDoubleExtra("VIDEO_DURATION", 0.0),
            intent.getStringExtra("VIDEO_ID")
            )

        playMovie(movie)
    }

    fun playMovie(movie: PlayerMediaModel) {
        val videoUrl = movie.videoUrl
        exoPlayerView = exoplayer_view

        try {
            val bandwidthMeter = DefaultBandwidthMeter.Builder(this).build()
            val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            val videoUri = Uri.parse(videoUrl)
            val dataSourceFactory = DefaultHttpDataSourceFactory("movie_player") //generalizar
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)

            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
            exoPlayerView.player = exoPlayer
            exoPlayer.prepare(mediaSource)
            exoPlayer.playWhenReady = true

        } catch (e: Exception) {
            Log.e("MoviePlayer", "exoplayer error$e")
        }
    }
}
