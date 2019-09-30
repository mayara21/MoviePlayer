package com.mayarafernandes.movieplayer.splash

import android.os.Handler
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Test

class SplashControllerTest {

    private val navigator = mock<SplashNavigator>()
    private val view = mock<SplashView>()
    private val handler = mock<Handler>()
    private val controller = SplashController(navigator, view, handler)


   @Test
    fun `test when call onViewCreated expect goToLogin after timer`() {
       setHandler()
       controller.onViewCreated()
       verify(navigator).goToLogin()
    }

    @Test
    fun `test when call onViewCreated expect finish after timer`() {
        setHandler()
        controller.onViewCreated()
        verify(view).finish()
    }

    private fun setHandler() {
        doAnswer {
            val runnable = it.arguments[0] as Runnable
            runnable.run()
            return@doAnswer true
        }.`when`(handler).postDelayed(any(), any())
    }
}