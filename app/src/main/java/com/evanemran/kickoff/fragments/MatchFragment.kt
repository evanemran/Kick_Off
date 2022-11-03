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
import com.evanemran.kickoff.database.RoomDB
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.FlyManager
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_team.*


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

//        manager.getAllMatches(allMatchesResponseListener)
//        manager.getStandings(groupStandingsListener)
        flyManager.getAllMatches(allMatchesResponseListener)
        flyManager.getTodayMatches(todayMatchesListener)

    }

    private val allMatchesResponseListener: ResponseListener<List<MatchDataFly>> = object : ResponseListener<List<MatchDataFly>>{
        override fun didFetch(message: String, response: List<MatchDataFly>) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            recycler_matches.setHasFixedSize(true)
            recycler_matches.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val adapter: MatchListAdapter = MatchListAdapter(requireContext(), response, matchClickListener)
            recycler_matches.adapter = adapter

            spin_kit_match.visibility = View.GONE
            scrollView.visibility = View.VISIBLE

        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private val todayMatchesListener: ResponseListener<List<MatchDataFly>> = object : ResponseListener<List<MatchDataFly>>{
        override fun didFetch(message: String, response: List<MatchDataFly>) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            recycler_today_match.setHasFixedSize(true)
            recycler_today_match.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val todayMatchAdapter: MatchListAdapter = MatchListAdapter(requireContext(), response, matchClickListener)
            recycler_today_match.adapter = todayMatchAdapter
        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
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