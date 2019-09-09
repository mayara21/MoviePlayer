package com.mayarafernandes.movieplayer.movieList.view

import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ToggleButton
import com.mayarafernandes.movieplayer.movieList.repository.Movie

class MovieListPresenter {

    fun convertModel(movie: Movie) = MovieViewModel(
        movie.title,
        movie.description,
        movie.images.find { it.type == "cover" }!!.url,
        movie.id,
        movie.favorite)

    fun setFavorite(favoriteButton: ToggleButton) {
        favoriteButton.startAnimation(setButtonFavoriteAnimation())
    }

    private fun setButtonFavoriteAnimation(): ScaleAnimation {
        var scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation.setDuration(500)
        var bounceInterpolator = BounceInterpolator()
        scaleAnimation.setInterpolator(bounceInterpolator)

        return scaleAnimation
    }
}