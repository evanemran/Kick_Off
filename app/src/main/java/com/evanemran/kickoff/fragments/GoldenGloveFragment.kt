package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.evanemran.kickoff.HistoryDetailsActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.HistoryAdapter
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.manager.BlobManager
import com.evanemran.kickoff.models.*
import kotlinx.android.synthetic.main.fragment_history.*

class GoldenGloveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wc_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager: BlobManager = BlobManager(requireContext())

//        manager.getAllTeams(allTeamsResponseListener)
//        manager.getAllWinners(allWinnersListener)

        setupHistoryMenu()

    }

    private fun setupHistoryMenu() {
        val hMenu: MutableList<HistoryMenu> = mutableListOf()

        hMenu.add(HistoryMenu.WINNERS)
        hMenu.add(HistoryMenu.WC_DETAILS)
        hMenu.add(HistoryMenu.GOLDEN_BALL)
        hMenu.add(HistoryMenu.GOLDEN_BOOT)
        hMenu.add(HistoryMenu.GOLDEN_GLOVE)
        hMenu.add(HistoryMenu.YOUNG_PLAYER)

        recycler_history.setHasFixedSize(true)
        recycler_history.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter: HistoryAdapter = HistoryAdapter(requireContext(), hMenu, historyClickListener)
        recycler_history.adapter = adapter
    }

//    private val allTeamsResponseListener: ResponseListener<ResponseWrapper<List<TeamInfo>>> = object : ResponseListener<ResponseWrapper<List<TeamInfo>>>{
//        override fun didFetch(message: String, response: ResponseWrapper<List<TeamInfo>>) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//
//            recycler_teams.setHasFixedSize(true)
//            recycler_teams.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            val adapter: TeamListAdapter = TeamListAdapter(requireContext(), response.data!!, teamClickListener)
//            recycler_teams.adapter = adapter
//
//        }
//        override fun didError(message: String) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//        }
//    }

//    private val allWinnersListener: ResponseListener<List<StandingsResponse>> = object : ResponseListener<List<StandingsResponse>>{
//        override fun didFetch(message: String, response: List<StandingsResponse>) {
////            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//
//            recycler_history.setHasFixedSize(true)
//            recycler_history.isNestedScrollingEnabled = false
//            recycler_history.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            val standingAdapter: StandingListAdapter = StandingListAdapter(requireContext(), response, teamClickListener)
//            recycler_history.adapter = standingAdapter
//
//            spin_kit_teams.visibility = View.GONE
//            scrollView_teams.visibility = View.VISIBLE
//
//        }
//        override fun didError(message: String) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//        }
//    }

    private val historyClickListener: ClickListener<HistoryMenu> = object : ClickListener<HistoryMenu> {
        override fun onClicked(data: HistoryMenu) {
            startActivity(Intent(requireContext(), HistoryDetailsActivity::class.java)
                .putExtra("history", data))
        }
    }
}