package com.evanemran.kickoff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.*
import com.evanemran.kickoff.manager.BlobManager
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_winner.*

class WinnersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_winner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager: BlobManager = BlobManager(requireContext())

        val animation: Sprite = Circle()
        spin_kit_winner.setIndeterminateDrawable(animation)

        showWinners()


    }

    private fun showWinners() {
        recycler_winners.setHasFixedSize(true)
        recycler_winners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter: WinnerListAdapter = WinnerListAdapter(requireContext(), winnerDetails)
        recycler_winners.adapter = adapter

        spin_kit_winner.visibility = View.GONE
        recycler_winners.visibility = View.VISIBLE
    }
}