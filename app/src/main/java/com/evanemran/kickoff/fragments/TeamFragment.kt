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
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.GroupsListAdapter
import com.evanemran.kickoff.adapters.TeamListAdapter
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager: RequestManager = RequestManager(requireContext())

//        manager.getAllTeams(allTeamsResponseListener)
        manager.getStandings(groupStandingsListener)

    }

    private val allTeamsResponseListener: ResponseListener<ResponseWrapper<List<TeamInfo>>> = object : ResponseListener<ResponseWrapper<List<TeamInfo>>>{
        override fun didFetch(message: String, response: ResponseWrapper<List<TeamInfo>>) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            recycler_teams.setHasFixedSize(true)
            recycler_teams.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val adapter: TeamListAdapter = TeamListAdapter(requireContext(), response.data!!, teamClickListener)
            recycler_teams.adapter = adapter

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
            val adapter: GroupsListAdapter = GroupsListAdapter(requireContext(), response.data!!, teamClickListener)
            recycler_groups.adapter = adapter

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
}