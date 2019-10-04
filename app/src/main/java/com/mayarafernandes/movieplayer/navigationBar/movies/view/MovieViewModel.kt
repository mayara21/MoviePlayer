package com.mayarafernandes.movieplayer.navigationBar.movies.view

data class MovieViewModel(var title: String,
                          var description: String,
                          var urlImage: String,
                          var id: String,
                          var isFavorite: Boolean,
                          var progress: Int)