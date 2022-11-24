package com.evanemran.kickoff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evanemran.kickoff.R
import com.evanemran.kickoff.models.MatchDataFly
import kotlinx.android.synthetic.main.fragment_lineup.*

class LineUpFragment(matchDataFly: MatchDataFly) : Fragment() {
    var matchData: MatchDataFly = matchDataFly

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lineup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView_venue.text = matchData.venue.toString()
        textView_location.text = matchData.location.toString()
        textView_stage.text = matchData.stage_name.toString()
        textView_attendance.text = matchData.attendance.toString() + " Attendance"

        textView_humidity.text = matchData.weather?.humidity.toString()
        textView_temp_celsius.text = matchData.weather?.temp_celsius.toString()
        textView_temp_farenheit.text = matchData.weather?.temp_farenheit.toString()
        textView_wind_speed.text = matchData.weather?.wind_speed.toString()
    }
}