package com.evanemran.kickoff.models

import java.io.Serializable

class MatchDataFly : Serializable {
    var id = 0
    var venue: String? = ""
    var location: String? = ""
    var status: String? = ""
    var weather: Weather? = null
    var attendance: String? = ""
    var officials: List<String>? = null
    var stage_name: String? = ""
    var home_team_country: String? = ""
    var away_team_country: String? = ""
    var datetime: String? = ""
    var winner: String? = ""
    var winner_code: String? = ""
    var home_team: HomeTeam? = null
    var away_team: AwayTeam? = null
    var last_checked_at: String? = ""
    var last_changed_at: String? = ""
}

class Weather : Serializable {
    var humidity: String? = ""
    var temp_celsius: String? = ""
    var temp_farenheit: String? = ""
    var wind_speed: String? = ""
    var description: String? = ""
}

class HomeTeam : Serializable {
    var country: String? = ""
    var name: String? = ""
    var goals: String? = ""
    var penalties: String? = ""
}

class AwayTeam : Serializable {
    var country: String? = ""
    var name: String? = ""
    var goals: String? = ""
    var penalties: String? = ""
}