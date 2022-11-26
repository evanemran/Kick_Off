package com.evanemran.kickoff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.GroupsListAdapter
import com.evanemran.kickoff.adapters.StandingListAdapter
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.FlyManager
import com.evanemran.kickoff.models.*
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.fragment_team.recycler_groups

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

        val manager: FlyManager = FlyManager(requireContext())

        val animation: Sprite = Circle()
        spin_kit_teams.setIndeterminateDrawable(animation)

        manager.getStandings(groupStandingsListener)

    }

    private val groupStandingsListener: ResponseListener<FlyStandingResponse> = object : ResponseListener<FlyStandingResponse>{
        override fun didFetch(message: String, response: FlyStandingResponse) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            recycler_groups.setHasFixedSize(true)
            recycler_groups.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val groupAdapter: GroupsListAdapter = GroupsListAdapter(requireContext(), response.groups)
            recycler_groups.adapter = groupAdapter

            recycler_standings.setHasFixedSize(true)
            recycler_standings.isNestedScrollingEnabled = false
            recycler_standings.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val standingAdapter: StandingListAdapter = StandingListAdapter(requireContext(), response.groups, standingClickListener)
            recycler_standings.adapter = standingAdapter

            spin_kit_teams.visibility = View.GONE
            scrollView_teams.visibility = View.VISIBLE

        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private val standingClickListener: ClickListener<FlyStats> = object : ClickListener<FlyStats> {
        override fun onClicked(data: FlyStats) {
            Toast.makeText(context, data.letter, Toast.LENGTH_SHORT).show()
        }

    }
}