package com.evanemran.kickoff

import android.app.PendingIntent.getActivity
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.adapters.DrawerAdapter
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.fragments.*
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.DrawerMenu
import com.evanemran.kickoff.utils.ExitDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selectedFragment: Fragment = MatchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        setupNavMenu()

        replaceFragment(selectedFragment)
        bottomNavBar.setOnNavigationItemSelectedListener(bottomMenuListener)
    }


    private fun setupNavMenu() {
        val navMenus: MutableList<DrawerMenu> = mutableListOf()
        navMenus.add(DrawerMenu.HOME)
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
                R.id.home -> {
                    if (selectedFragment !is MatchFragment){
                        selectedFragment = MatchFragment()
                        replaceFragment(MatchFragment())
                    }
                }
                R.id.teams -> {
                    if (selectedFragment !is TeamFragment){
                        selectedFragment = TeamFragment()
                        replaceFragment(TeamFragment())
                    }
                }
                R.id.match -> replaceFragment(TeamFragment())
                R.id.stands -> {
                    if (selectedFragment !is ProfileFragment){
                        selectedFragment = ProfileFragment()
                        replaceFragment(ProfileFragment())
                    }
                }
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
                DrawerMenu.HOME -> {
                    if (selectedFragment !is MatchFragment){
                        selectedFragment = MatchFragment()
                        replaceFragment(MatchFragment())
                    }
                }
                DrawerMenu.STATS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.HISTORY -> {
                    if (selectedFragment !is HistoryFragment){
                        selectedFragment = HistoryFragment()
                        replaceFragment(HistoryFragment())
                    }
                }
                DrawerMenu.BLOGS -> {
                    if (selectedFragment !is BlogFragment){
                        selectedFragment = BlogFragment()
                        replaceFragment(BlogFragment())
                    }
                }
                DrawerMenu.HELP_CENTER -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.SETTINGS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.TERMS -> Toast.makeText(this@MainActivity, "Will be added soon!", Toast.LENGTH_SHORT).show()
                DrawerMenu.LOGOUT -> logout()
            }

            drawer_layout.closeDrawer(GravityCompat.START)
        }

    }

    private fun logout() {
        SharedPrefs(this).saveToken("")
        startActivity(Intent(this, AuthActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        this@MainActivity.finish()
    }

    override fun onBackPressed() {
        val dialog: ExitDialog = ExitDialog()
        dialog.show(supportFragmentManager, "Category")
    }


}