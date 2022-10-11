package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.evanemran.kickoff.fragments.MatchFragment
import com.evanemran.kickoff.fragments.TeamFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(TeamFragment())
        bottomNavBar.setOnNavigationItemSelectedListener(bottomMenuListener)
    }

    private val bottomMenuListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(TeamFragment())
                R.id.teams -> replaceFragment(MatchFragment())
                R.id.match -> replaceFragment(TeamFragment())
                R.id.stands -> replaceFragment(TeamFragment())
            }
            true
        }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}