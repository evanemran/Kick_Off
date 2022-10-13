package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evanemran.kickoff.models.HistoryMenu

class HistoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_details)

        var hMenu: HistoryMenu = intent.getSerializableExtra("history") as HistoryMenu

        replaceFragment(hMenu.fragment)


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.hist_fragments_container, fragment)
        fragmentTransaction.commit()
    }
}