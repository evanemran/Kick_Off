package com.evanemran.kickoff

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.adapters.DrawerAdapter
import com.evanemran.kickoff.database.FirebaseDbConstants
import com.evanemran.kickoff.fragments.*
import com.evanemran.kickoff.globals.GlobalUser
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.DrawerMenu
import com.evanemran.kickoff.models.MatchDataFly
import com.evanemran.kickoff.models.User
import com.evanemran.kickoff.utils.ExitDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*


var fixtureList: List<MatchDataFly> = listOf()
var todayMatch: List<MatchDataFly> = listOf()

class MainActivity : AppCompatActivity() {

    var selectedFragment: Fragment = MatchFragment()
    var user: User = User()
    lateinit var databaseReference: DatabaseReference//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseDbConstants.TABLE_USER)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val user = FirebaseAuth.getInstance().currentUser

        val uID = FirebaseAuth.getInstance().currentUser!!.uid
//        if (uID.isNotEmpty()) {
//            getUserData(uID)
//        }
        getUserData(uID)

        setupNavMenu()

        replaceFragment(selectedFragment)
        bottomNavBar.setOnNavigationItemSelectedListener(bottomMenuListener)


    }

    private fun getUserData(uID: String) {
        try{
            val dbQuery = databaseReference
                .orderByChild("userId")
                .equalTo(uID)
            dbQuery.addListenerForSingleValueEvent(
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (postSnapshot in dataSnapshot.children) {
                            user = postSnapshot.getValue(User::class.java)!!
                            GlobalUser.getInstance().data = (user)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
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


        textView_username.text = "Welcome " +  user.userName
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
                R.id.match -> {
                    if (selectedFragment !is FeedFragment){
                        selectedFragment = FeedFragment()
                        replaceFragment(FeedFragment())
                    }
                }
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
                    if (selectedFragment !is WcHistoryFragment){
                        selectedFragment = WcHistoryFragment()
                        replaceFragment(WcHistoryFragment())
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

//    private fun logout() {
//        SharedPrefs(this).saveToken("")
//        startActivity(Intent(this, AuthActivity::class.java)
//            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//        this@MainActivity.finish()
//    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }

//    override fun onBackPressed() {
//        val dialog: ExitDialog = ExitDialog()
//        dialog.show(supportFragmentManager, "Category")
//    }


}