package com.evanemran.kickoff.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.TimeLineAdapter
import com.evanemran.kickoff.models.AwayTeamEvent
import com.evanemran.kickoff.models.HomeTeamEvent
import com.evanemran.kickoff.models.TimelineData
import kotlinx.android.synthetic.main.fragment_timeline.*

class TimeLineFragment(hEvents: List<HomeTeamEvent>, aEvents: List<AwayTeamEvent>) : Fragment() {

    var homeList : List<HomeTeamEvent> = hEvents
    var awayList : List<AwayTeamEvent> = aEvents

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showTimeLineDemo()
    }

    private fun showTimeLineDemo() {
        var tList: MutableList<TimelineData> = mutableListOf()
        homeList.forEach {
            tList.add(TimelineData(it.player.toString(), it.type_of_event.toString(), "https://avatarfiles.alphacoders.com/244/244597.jpg", it.time.toString(), it.extra_info.toString()))
        }
        awayList.forEach {
            tList.add(TimelineData(it.player.toString(), it.type_of_event.toString(), "https://avatarfiles.alphacoders.com/244/244597.jpg", it.time.toString(), it.extra_info.toString()))
        }

        recycler_timeline.setHasFixedSize(true)
        recycler_timeline.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: TimeLineAdapter = TimeLineAdapter(requireContext(), tList)
        recycler_timeline.adapter = adapter
    }
}