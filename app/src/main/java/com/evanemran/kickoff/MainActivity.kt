package com.evanemran.kickoff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.adapters.DrawerAdapter
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.fragments.MatchFragment
import com.evanemran.kickoff.fragments.TeamFragment
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.DrawerMenu
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

        setupNavMenu()

        replaceFragment(MatchFragment())
        bottomNavBar.setOnNavigationItemSelectedListener(bottomMenuListener)
    }


    private fun setupNavMenu() {
        val navMenus: MutableList<DrawerMenu> = mutableListOf()
        navMenus.add(DrawerMenu.STATS)
        navMenus.add(DrawerMenu.HISTORY)
        navMenus.add(DrawerMenu.BLOGS)
        navMenus.add(DrawerMenu.HELP_CENTER)
        navMenus.add(DrawerMenu.SETTINGS)
        navMenus.add(DrawerMenu.TERMS)
        navMenus.add(DrawerMenu.LOGOUT)

        recycler_nav.setHasFixedSize(true)
        recycler_nav.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val drawerAdapter = DrawerAdapter(this, navMenus, drawerClickListener)
        recycler_nav.adapter = drawerAdapter
    }

    private val bottomMenuListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(MatchFragment())
                R.id.teams -> replaceFragment(TeamFragment())
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

    private val drawerClickListener: ClickListener<DrawerMenu> = object : ClickListener<DrawerMenu>{
        override fun onClicked(data: DrawerMenu) {
            when (data) {
                DrawerMenu.STATS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.HISTORY -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.BLOGS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.HELP_CENTER -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.SETTINGS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.TERMS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.LOGOUT -> logout()
            }
        }

    }

    private fun logout() {
        SharedPrefs(this).saveToken("")
        startActivity(Intent(this, AuthActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        this@MainActivity.finish()
    }
}