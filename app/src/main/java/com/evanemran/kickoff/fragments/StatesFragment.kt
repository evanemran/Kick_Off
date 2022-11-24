package com.evanemran.kickoff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.StatesListAdapter
import com.evanemran.kickoff.adapters.TimeLineAdapter
import com.evanemran.kickoff.models.AwayTeamStatistics
import com.evanemran.kickoff.models.HomeTeamStatistics
import com.evanemran.kickoff.models.StatesData
import kotlinx.android.synthetic.main.fragment_states.*
import kotlinx.android.synthetic.main.fragment_timeline.*

class StatesFragment(hStats: HomeTeamStatistics?, aStats: AwayTeamStatistics?) : Fragment() {

    var home: HomeTeamStatistics? = hStats
    var away: AwayTeamStatistics? = aStats

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_states, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStatesDemo()
    }

    private fun setupStatesDemo() {
        val sList: MutableList<StatesData> = mutableListOf()

        sList.add(StatesData("Possession %", 50, 50))
        sList.add(StatesData("Shot", home!!.attempts_on_goal, away!!.attempts_on_goal))
        sList.add(StatesData("Shot on Goal", home!!.on_target, away!!.on_target))
        sList.add(StatesData("Yellow Card", home!!.yellow_cards, away!!.yellow_cards))
        sList.add(StatesData("Red Card", home!!.red_cards, away!!.red_cards))
        sList.add(StatesData("Corner Kicks", home!!.corners, away!!.corners))
        sList.add(StatesData("Free Kicks", home!!.free_kicks, away!!.free_kicks))
        sList.add(StatesData("Goal Kicks", home!!.goal_kicks, away!!.goal_kicks))
        sList.add(StatesData("Penalties", home!!.penalties, away!!.penalties))
        sList.add(StatesData("Throw Ins", home!!.throw_ins, away!!.throw_ins))
        sList.add(StatesData("Blocked", home!!.blocked, away!!.blocked))
        sList.add(StatesData("Corners", home!!.corners, away!!.corners))
        sList.add(StatesData("Offsides", home!!.offsides, away!!.offsides))
        sList.add(StatesData("Passes", home!!.num_passes, away!!.num_passes))
        sList.add(StatesData("Tackles", home!!.tackles, away!!.tackles))
        sList.add(StatesData("Fouls", home!!.fouls_committed, away!!.fouls_committed))

        recycler_states.setHasFixedSize(true)
        recycler_states.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: StatesListAdapter = StatesListAdapter(requireContext(), sList)
        recycler_states.adapter = adapter
    }
}