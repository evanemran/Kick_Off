package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evanemran.kickoff.fragments.winnerList
import com.evanemran.kickoff.models.WinnerDetails
import com.evanemran.kickoff.models.WorldCups
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_history_details.*

class HistoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_details)

        val hMenu: WorldCups = intent.getSerializableExtra("history") as WorldCups

        toolbar_history_title.title = hMenu.title
        Picasso.get().load(hMenu.image).into(imageView_icon)

        winnerList.forEach {
            if (it.Year == hMenu.year){
                setupData(it)
            }
        }
    }

    private fun setupData(it: WinnerDetails) {
        Picasso.get().load("https://countryflagsapi.com/png/" + it.Winner).into(imageView_winner)
        Picasso.get().load("https://countryflagsapi.com/png/" + it.RunnersUp).into(imageView_runnerUp)
        Picasso.get().load("https://countryflagsapi.com/png/" + it.Third).into(imageView_third)
        Picasso.get().load("https://countryflagsapi.com/png/" + it.Fourth).into(imageView_fourth)

        textView_host.text = it.Country
        textView_qualified.text = it.QualifiedTeams.toString()
        textView_matches.text = it.MatchesPlayed.toString()
        textView_goals.text = it.GoalsScored.toString()
        textView_attendance.text = it.Attendance

        textView_winner.text = it.Winner
        textView_runnerUp.text = it.RunnersUp
        textView_third.text = it.Third
        textView_fourth.text = it.Fourth
    }
}