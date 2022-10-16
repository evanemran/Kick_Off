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
import com.evanemran.kickoff.models.StatesData
import kotlinx.android.synthetic.main.fragment_states.*
import kotlinx.android.synthetic.main.fragment_timeline.*

class StatesFragment : Fragment() {

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

        sList.add(StatesData("Possession %", 30, 70))
        sList.add(StatesData("Shot", 2, 12))
        sList.add(StatesData("Shot on Goal", 1, 8))
        sList.add(StatesData("Yellow Card", 1, 0))
        sList.add(StatesData("Red Card", 0, 0))
        sList.add(StatesData("Corner Kicks", 1, 4))
        sList.add(StatesData("Goalkeeper Saved", 6, 1))
        sList.add(StatesData("Crosses", 4, 4))

        recycler_states.setHasFixedSize(true)
        recycler_states.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: StatesListAdapter = StatesListAdapter(requireContext(), sList)
        recycler_states.adapter = adapter
    }
}