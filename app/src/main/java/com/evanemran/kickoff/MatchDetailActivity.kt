package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evanemran.kickoff.adapters.MatchPagerAdapter
import com.evanemran.kickoff.fragments.LineUpFragment
import com.evanemran.kickoff.fragments.StatesFragment
import com.evanemran.kickoff.fragments.TimeLineFragment
import com.evanemran.kickoff.models.MatchDataFly
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val matchData: MatchDataFly = intent.getSerializableExtra("data") as MatchDataFly

        Picasso.get().load("https://countryflagsapi.com/png/" + matchData.home_team?.name).into(imageView_homeImage)
        Picasso.get().load("https://countryflagsapi.com/png/" + matchData.away_team?.name).into(imageView_awayImage)

        textView_homeName.text = matchData.home_team?.country
        textView_awayName.text = matchData.away_team?.country


        var pagerAdapter: MatchPagerAdapter = MatchPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(StatesFragment(), "States")
        pagerAdapter.addFragment(TimeLineFragment(), "Timeline")
        pagerAdapter.addFragment(LineUpFragment(), "Line-Up")

        view_pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }
}