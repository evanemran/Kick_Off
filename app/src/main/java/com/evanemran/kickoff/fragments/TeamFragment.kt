package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.evanemran.kickoff.MainActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import kotlinx.android.synthetic.main.fragment_login.*

class TeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var manager: RequestManager = RequestManager(requireContext())

        manager.getAllTeams(allTeamsResponseListener)

    }

    private val allTeamsResponseListener: ResponseListener<ResponseWrapper<List<TeamInfo>>> = object : ResponseListener<ResponseWrapper<List<TeamInfo>>>{
        override fun didFetch(message: String, response: ResponseWrapper<List<TeamInfo>>) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}