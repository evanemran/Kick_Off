package com.evanemran.kickoff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.TimeLineAdapter
import com.evanemran.kickoff.models.TimelineData
import kotlinx.android.synthetic.main.fragment_timeline.*

class TimeLineFragment : Fragment() {

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

        tList.add(TimelineData("Leonel Messi", "GOAL!", "https://avatarfiles.alphacoders.com/244/244597.jpg", "25'"))
        tList.add(TimelineData("Xavi", "Yellow Card!", "https://www.wowkeren.com/images/photo/xavi_hernandez.jpg", "31'"))
        tList.add(TimelineData("Andres Iniesta", "Fowl!", "http://4.bp.blogspot.com/-fKy0_B57V-4/Twm2prxETvI/AAAAAAAAAEE/iaI5MCXOwuA/s1600/Andres+Iniesta6.jpg", "42'"))
        tList.add(TimelineData("Sergio Busquets", "Substitute!", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVddr1-yt7H4rcbvowMlJ-Llt_EgYYtJpSpOUJNhpUWMbeEGsuhM7NVKExCKcR7aNA2Q0&usqp=CAU", "62'"))

        recycler_timeline.setHasFixedSize(true)
        recycler_timeline.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: TimeLineAdapter = TimeLineAdapter(requireContext(), tList)
        recycler_timeline.adapter = adapter
    }
}