package com.mayarafernandes.movieplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        setNavigationListener()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MovieListFragment()).commit()
    }

    private fun setNavigationListener() {
        val bottomNavigation: BottomNavigationView = bottom_navigation

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            val itemId = item.itemId

            if(itemId == R.id.nav_movies) selectedFragment = MovieListFragment()
            else if(itemId == R.id.nav_favorites) selectedFragment = FavoriteListFragment()

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment!!).commit()

            true
        }
    }
}
