package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evanemran.kickoff.models.HistoryMenu
import kotlinx.android.synthetic.main.activity_history_details.*

class HistoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_details)

        val hMenu: HistoryMenu = intent.getSerializableExtra("history") as HistoryMenu

        toolbar_history_title.title = hMenu.title
        replaceFragment(hMenu.fragment)


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.hist_fragments_container, fragment)
        fragmentTransaction.commit()
    }
}