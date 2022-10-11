package com.evanemran.kickoff.models

import java.io.Serializable

class MatchDataFly : Serializable {
    var id = 0
    var venue: String? = null
    var location: String? = null
    var status: String? = null
    var weather: Weather? = null
    var attendance: Any? = null
    var officials: ArrayList<Any>? = null
    var stage_name: String? = null
    var home_team_country: String? = null
    var away_team_country: String? = null
    var datetime: String? = null
    var winner: Any? = null
    var winner_code: Any? = null
    var home_team: HomeTeam? = null
    var away_team: AwayTeam? = null
    var last_checked_at: String? = null
    var last_changed_at: String? = null
}

class Weather : Serializable {
    var humidity: Any? = null
    var temp_celsius: Any? = null
    var temp_farenheit: Any? = null
    var wind_speed: Any? = null
    var description: Any? = null
}

class HomeTeam : Serializable {
    var country: String? = null
    var name: String? = null
    var goals: Any? = null
    var penalties: Any? = null
}

class AwayTeam : Serializable {
    var country: String? = null
    var name: String? = null
    var goals: Any? = null
    var penalties: Any? = null
}