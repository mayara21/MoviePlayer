package com.mayarafernandes.movieplayer.navigationBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.KeepWatchingListFragment
import com.mayarafernandes.movieplayer.navigationBar.favorites.FavoriteListFragment
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        setNavigationListener()

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MovieListFragment()
        ).commit()
    }

    private fun setNavigationListener() {
        val bottomNavigation: BottomNavigationView = bottom_navigation

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_movies -> selectedFragment =
                    MovieListFragment()
                R.id.nav_favorites -> selectedFragment =
                    FavoriteListFragment()
                R.id.nav_keep_watching -> selectedFragment =
                    KeepWatchingListFragment()
            }

            supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(
                R.id.fragment_container, selectedFragment!!).commitAllowingStateLoss()

            true
        }
    }
}
