package com.mayarafernandes.movieplayer

data class PlayerMediaModel(var id: String,
                            var title: String,
                            var description: String,
                            var videoUrl: String,
                            var duration: Double,
                            var videoId: String)