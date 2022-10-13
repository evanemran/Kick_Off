package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.HistoryDetailsActivity
import com.evanemran.kickoff.MainActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.GroupsListAdapter
import com.evanemran.kickoff.adapters.HistoryAdapter
import com.evanemran.kickoff.adapters.StandingListAdapter
import com.evanemran.kickoff.adapters.TeamListAdapter
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.BlobManager
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.fragment_team.recycler_groups
import kotlinx.android.synthetic.main.fragment_team.recycler_teams

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
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