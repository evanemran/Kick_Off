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
import com.evanemran.kickoff.adapters.*
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
import kotlinx.android.synthetic.main.fragment_wc_stats.*
import kotlinx.android.synthetic.main.fragment_winner.*

class WcStatsFragment : Fragment() {

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

        setupStats()

    }

    private fun setupStats() {
        recycler_stats.setHasFixedSize(true)
        recycler_stats.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: WinnerListAdapter = WinnerListAdapter(requireContext(), winnerDetails)
        recycler_stats.adapter = adapter
    }

    private val historyClickListener: ClickListener<HistoryMenu> = object : ClickListener<HistoryMenu> {
        override fun onClicked(data: HistoryMenu) {
            startActivity(Intent(requireContext(), HistoryDetailsActivity::class.java)
                .putExtra("history", data))
        }
    }
}