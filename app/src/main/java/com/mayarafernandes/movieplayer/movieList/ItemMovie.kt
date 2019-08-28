package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.service.TempService

class ItemMovie (){

    private val tempService = TempService()

    fun returnMovieList() = tempService.returnMovieList()

}