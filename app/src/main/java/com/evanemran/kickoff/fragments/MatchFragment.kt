package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.MainActivity
import com.evanemran.kickoff.MatchDetailActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.GroupsListAdapter
import com.evanemran.kickoff.adapters.MatchListAdapter
import com.evanemran.kickoff.adapters.StandingListAdapter
import com.evanemran.kickoff.adapters.TeamListAdapter
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.FlyManager
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.fragment_team.recycler_groups

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

//        manager.getAllMatches(allMatchesResponseListener)
        manager.getStandings(groupStandingsListener)
        flyManager.getAllMatches(allMatchesResponseListener)

    }

    private val allMatchesResponseListener: ResponseListener<List<MatchDataFly>> = object : ResponseListener<List<MatchDataFly>>{
        override fun didFetch(message: String, response: List<MatchDataFly>) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            recycler_matches.setHasFixedSize(true)
            recycler_matches.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val adapter: MatchListAdapter = MatchListAdapter(requireContext(), response, matchClickListener)
            recycler_matches.adapter = adapter

        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private val groupStandingsListener: ResponseListener<ResponseWrapper<List<StandingsResponse>>> = object : ResponseListener<ResponseWrapper<List<StandingsResponse>>>{
        override fun didFetch(message: String, response: ResponseWrapper<List<StandingsResponse>>) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            recycler_groups.setHasFixedSize(true)
            recycler_groups.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val groupAdapter: GroupsListAdapter = GroupsListAdapter(requireContext(), response.data!!, teamClickListener)
            recycler_groups.adapter = groupAdapter

//            recycler_standings.setHasFixedSize(true)
//            recycler_standings.isNestedScrollingEnabled = false
//            recycler_standings.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            val standingAdapter: StandingListAdapter = StandingListAdapter(requireContext(), response.data!!, teamClickListener)
//            recycler_standings.adapter = standingAdapter

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