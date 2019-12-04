package com.mayarafernandes.movieplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.item_player.*

class MovieActivity : AppCompatActivity() {

    private lateinit var exoPlayerView: PlayerView
    private lateinit var exoPlayer: SimpleExoPlayer
    private var videoUrl = "http://d2bqeap5aduv6p.cloudfront.net/project_coderush_640x360_521kbs_56min.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        exoPlayerView = exoplayer_view

        try {
            val bandwidthMeter = DefaultBandwidthMeter.Builder(this).build()
            val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            val renderers = DefaultRenderersFactory(this)
            val loadControl = DefaultLoadControl()

            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
            val videoUri = Uri.parse(videoUrl)
            val dataSourceFactory = DefaultHttpDataSourceFactory("movie_player") //generalizar
            val extractorsFactory = DefaultExtractorsFactory()
            val mediaSource = ExtractorMediaSource(videoUri, dataSourceFactory, extractorsFactory, null, null)

            exoPlayerView.player = exoPlayer
            exoPlayer.prepare(mediaSource)
            exoPlayer.playWhenReady = true

        } catch (e: Exception) {
            Log.e("MoviePlayer", "exoplayer error$e")
        }
    }
}
