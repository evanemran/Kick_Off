package com.evanemran.kickoff.models

import java.io.Serializable

class MatchDataFly : Serializable {
    var id = 0
    var venue: String? = ""
    var location: String? = ""
    var status: String? = ""
    var weather: Weather? = null
    var attendance: String? = ""
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
    var time: String? = ""
    var detailed_time: DetailedTime? = null
    var officials: List<Officials> = listOf()
    var home_team_events: List<HomeTeamEvent> = listOf()
    var away_team_events: List<AwayTeamEvent> = listOf()
    var home_team_lineup: HomeTeamLineup? = null
    var away_team_lineup: AwayTeamLineup? = null
    var home_team_statistics: HomeTeamStatistics? = null
    var away_team_statistics: AwayTeamStatistics? = null
}

class Weather : Serializable {
    var humidity: String? = ""
    var temp_celsius: String? = ""
    var temp_farenheit: String? = ""
    var wind_speed: String? = ""
    var description: String? = ""
}

class DetailedTime : Serializable {
    var current_time: String = ""
    var first_half_time: String = ""
    var first_half_extra_time: String = ""
    var second_half_time: String = ""
    var second_half_extra_time: String = ""
}

class HomeTeam : Serializable {
    var country: String? = ""
    var name: String? = ""
    var goals: Int? = 0
    var penalties: Int? = 0
}

class HomeTeamEvent {
    var id = 0
    var type_of_event: String? = null
    var player: String? = null
    var time: String? = null
    var extra_info: String? = null
}

class HomeTeamLineup {
    var country: String? = null
    var tactics: String? = null
    var starting_eleven: ArrayList<StartingEleven>? = null
    var substitutes: ArrayList<Substitute>? = null
}

class HomeTeamStatistics {
    var country: String? = null
    var attempts_on_goal = 0
    var attempts_on_goal_against = 0
    var on_target = 0
    var off_target = 0
    var blocked = 0
    var corners = 0
    var offsides = 0
    var num_passes = 0
    var passes_completed = 0
    var tackles = 0
    var free_kicks = 0
    var goal_kicks = 0
    var penalties = 0
    var penalties_scored = 0
    var throw_ins = 0
    var yellow_cards = 0
    var red_cards = 0
    var fouls_committed = 0
}

class StartingEleven {
    var name: String? = null
    var shirt_number = 0
    var position: String? = null
}

class Substitute {
    var name: String? = null
    var shirt_number = 0
    var position: String? = null
}

class Officials : Serializable {
    var name: String = ""
    var role: String = ""
    var country: String = ""
}

class AwayTeam : Serializable {
    var country: String? = ""
    var name: String? = ""
    var goals: String? = ""
    var penalties: String? = ""
}

class AwayTeamEvent {
    var id = 0
    var type_of_event: String? = null
    var player: String? = null
    var time: String? = null
    var extra_info: String? = null
}

class AwayTeamLineup {
    var country: String? = null
    var tactics: String? = null
    var starting_eleven: ArrayList<StartingEleven>? = null
    var substitutes: ArrayList<Substitute>? = null
}

class AwayTeamStatistics {
    var country: String? = null
    var attempts_on_goal = 0
    var attempts_on_goal_against = 0
    var on_target = 0
    var off_target = 0
    var blocked = 0
    var corners = 0
    var offsides = 0
    var num_passes = 0
    var passes_completed = 0
    var tackles = 0
    var free_kicks = 0
    var goal_kicks = 0
    var penalties = 0
    var penalties_scored = 0
    var throw_ins = 0
    var yellow_cards = 0
    var red_cards = 0
    var fouls_committed = 0
}