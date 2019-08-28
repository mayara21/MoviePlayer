package com.mayarafernandes.movieplayer.movieList.view

data class MovieViewModel(var title: String = "", var description: String = "", var urlImage: String = "")

val movies = listOf<MovieViewModel>(
    MovieViewModel(
        "Amnesia",
        "Filme confuso do Nolan",
        "https://upload.wikimedia.org/wikipedia/pt/thumb/c/cf/MementoCartaz.jpg/250px-MementoCartaz.jpg"
    ),
    MovieViewModel(
        "Moana",
        "Animacao da Disney de 2017",
        "https://assets.xtechcommerce.com/uploads/images/medium/cdc3456c1b88331938c4102d9221ab2c.jpg"
    ))


