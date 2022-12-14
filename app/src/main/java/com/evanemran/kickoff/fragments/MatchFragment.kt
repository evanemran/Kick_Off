package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.MatchDetailActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.MatchListAdapter
import com.evanemran.kickoff.adapters.SliderAdapter
import com.evanemran.kickoff.fixtureList
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.FlyManager
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import com.evanemran.kickoff.todayMatch
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_match.*


class MatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager: RequestManager = RequestManager(requireContext())
        val flyManager: FlyManager = FlyManager(requireContext())

        val animation: Sprite = Circle()
        spin_kit_match.setIndeterminateDrawable(animation)

        flyManager.getAllMatches(allMatchesResponseListener)
        flyManager.getTodayMatches(todayMatchesListener)

        /*if(fixtureList.isEmpty()){
            flyManager.getAllMatches(allMatchesResponseListener)
        }
        else {
            showFixture(fixtureList)
        }


        if(todayMatch.isEmpty()){
            flyManager.getTodayMatches(todayMatchesListener)
        }
        else {
            showTodayMatches(todayMatch)
        }*/

    }

    private val allMatchesResponseListener: ResponseListener<List<MatchDataFly>> = object : ResponseListener<List<MatchDataFly>>{
        override fun didFetch(message: String, response: List<MatchDataFly>) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            fixtureList = response

            showFixture(fixtureList)

        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showFixture(fixtureList: List<MatchDataFly>) {
        recycler_matches.setHasFixedSize(true)
        recycler_matches.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: MatchListAdapter = MatchListAdapter(requireContext(), fixtureList, matchClickListener)
        recycler_matches.adapter = adapter

        spin_kit_match.visibility = View.GONE
        scrollView.visibility = View.VISIBLE

    }

    private val todayMatchesListener: ResponseListener<List<MatchDataFly>> = object : ResponseListener<List<MatchDataFly>>{
        override fun didFetch(message: String, response: List<MatchDataFly>) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            todayMatch = response
            showTodayMatches(todayMatch)

//            recycler_today_match.setHasFixedSize(true)
//            recycler_today_match.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            val todayMatchAdapter: MatchListAdapter = MatchListAdapter(requireContext(), response, matchClickListener)
//            recycler_today_match.adapter = todayMatchAdapter
        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showTodayMatches(todayMatch: List<MatchDataFly>) {
        var sliderAdapter = SliderAdapter(requireContext(), todayMatch)
        slider.setSliderAdapter(sliderAdapter)
        slider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        slider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        slider.startAutoCycle()
    }

    private val teamClickListener: ClickListener<TeamInfo> = object : ClickListener<TeamInfo> {
        override fun onClicked(data: TeamInfo) {
            Toast.makeText(context, data.fifa_code, Toast.LENGTH_SHORT).show()
        }

    }

    private val matchClickListener: ClickListener<MatchDataFly> = object : ClickListener<MatchDataFly> {
        override fun onClicked(data: MatchDataFly) {
            startActivity(Intent(requireContext(), MatchDetailActivity::class.java)
                .putExtra("data", data))
        }

    }
}