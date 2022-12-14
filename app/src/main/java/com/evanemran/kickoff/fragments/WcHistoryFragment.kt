package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.evanemran.kickoff.HistoryDetailsActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.*
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.BlobManager
import com.evanemran.kickoff.models.*
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_wc_history.*

var winnerList: List<WinnerDetails> = listOf()

class WcHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wc_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation: Sprite = Circle()
        spin_kit_wc_history.setIndeterminateDrawable(animation)

        var manager: BlobManager = BlobManager(requireContext())
        manager.getDetails(winnerDetailsListener)

    }

    private val winnerDetailsListener: ResponseListener<List<WinnerDetails>> = object :
        ResponseListener<List<WinnerDetails>> {
        override fun didFetch(message: String, response: List<WinnerDetails>) {
            winnerList = response
            setupWcHistory()
            spin_kit_wc_history.visibility = View.GONE
            recycler_wc_history.visibility = View.VISIBLE
        }

        override fun didError(message: String) {
            spin_kit_wc_history.visibility = View.GONE
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupWcHistory() {
        val hList: MutableList<WorldCups> = mutableListOf()

        hList.add(WorldCups.RUSSIA)
        hList.add(WorldCups.BRAZIL)
        hList.add(WorldCups.AFRICA)
        hList.add(WorldCups.GERMANY)
        hList.add(WorldCups.KOREA_JAPAN)
        hList.add(WorldCups.FRANCE)
        hList.add(WorldCups.USA)
        hList.add(WorldCups.ITALY)
        hList.add(WorldCups.MEXICO)
        hList.add(WorldCups.ESPANA)
        hList.add(WorldCups.ARGENTINA)
        hList.add(WorldCups.WEST_GERMANY)
        hList.add(WorldCups.MEXICO70)
        hList.add(WorldCups.ENGLAND)
        hList.add(WorldCups.CHILE)
        hList.add(WorldCups.SWEDEN)
        hList.add(WorldCups.SWITZERLAND)
        hList.add(WorldCups.BRAZIL50)
        hList.add(WorldCups.FRANCE38)
        hList.add(WorldCups.ITALY34)
        hList.add(WorldCups.URUGUAY)

        recycler_wc_history.setHasFixedSize(true)
        recycler_wc_history.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter: WorldCupsAdapter = WorldCupsAdapter(requireContext(), hList, worldCupsClickListener)
        recycler_wc_history.adapter = adapter
    }

    private val worldCupsClickListener: ClickListener<WorldCups> = object : ClickListener<WorldCups> {
        override fun onClicked(data: WorldCups) {
            startActivity(Intent(requireContext(), HistoryDetailsActivity::class.java)
                .putExtra("history", data))
        }
    }
}