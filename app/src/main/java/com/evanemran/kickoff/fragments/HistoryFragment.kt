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
import com.evanemran.kickoff.adapters.HistoryAdapter
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.BlobManager
import com.evanemran.kickoff.models.*
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_history.*

var winnerDetails : List<WinnerDetails> = listOf()

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

        val animation: Sprite = Circle()
        spin_kit_history.setIndeterminateDrawable(animation)

        manager.getDetails(winnerDetailsListener)

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

    private val historyClickListener: ClickListener<HistoryMenu> = object : ClickListener<HistoryMenu> {
        override fun onClicked(data: HistoryMenu) {
            startActivity(Intent(requireContext(), HistoryDetailsActivity::class.java)
                .putExtra("history", data))
        }
    }

    private val winnerDetailsListener: ResponseListener<List<WinnerDetails>> = object : ResponseListener<List<WinnerDetails>> {
        override fun didFetch(message: String, response: List<WinnerDetails>) {
            winnerDetails = response
            setupHistoryMenu()
            spin_kit_history.visibility = View.GONE
            recycler_history.visibility = View.VISIBLE
        }

        override fun didError(message: String) {
            spin_kit_history.visibility = View.GONE
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}