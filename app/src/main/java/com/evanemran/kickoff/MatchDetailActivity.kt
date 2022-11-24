package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.evanemran.kickoff.adapters.MatchPagerAdapter
import com.evanemran.kickoff.adapters.SliderAdapter
import com.evanemran.kickoff.fragments.LineUpFragment
import com.evanemran.kickoff.fragments.StatesFragment
import com.evanemran.kickoff.fragments.TimeLineFragment
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.FlyManager
import com.evanemran.kickoff.models.MatchDataFly
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.fragment_match.*

class MatchDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val flyManager: FlyManager = FlyManager(this)

        val matchData: MatchDataFly = intent.getSerializableExtra("data") as MatchDataFly

        flyManager.getMatchData(matchData.id, matchDetailsListener)

        Picasso.get().load("https://countryflagsapi.com/png/" + matchData.home_team?.name).into(imageView_homeImage)
        Picasso.get().load("https://countryflagsapi.com/png/" + matchData.away_team?.name).into(imageView_awayImage)

        textView_homeName.text = matchData.home_team?.country
        textView_awayName.text = matchData.away_team?.country
    }

    private val matchDetailsListener: ResponseListener<MatchDataFly> = object :
        ResponseListener<MatchDataFly> {
        override fun didFetch(message: String, response: MatchDataFly) {
            match_score.text = response.home_team?.goals.toString() + " : " + response.away_team?.goals.toString()
            match_time.text = response.time


            val pagerAdapter: MatchPagerAdapter = MatchPagerAdapter(supportFragmentManager)
            pagerAdapter.addFragment(StatesFragment(response.home_team_statistics, response.away_team_statistics), "Stats")
            pagerAdapter.addFragment(TimeLineFragment(response.home_team_events, response.away_team_events), "Timeline")
            pagerAdapter.addFragment(LineUpFragment(response), "Info")

            view_pager.adapter = pagerAdapter
            tab_layout.setupWithViewPager(view_pager)

        }
        override fun didError(message: String) {
            Toast.makeText(this@MatchDetailActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}