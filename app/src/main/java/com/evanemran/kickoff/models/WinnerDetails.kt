package com.evanemran.kickoff.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WinnerDetails : Serializable {
    var Year: Int = 0
    var Country: String = ""
    var Winner: String = ""

    @SerializedName("Runners-Up")
    var RunnersUp: String = ""

    var Third: String = ""
    var Fourth: String = ""
    var GoalsScored: Int = 0
    var QualifiedTeams: Int = 0
    var MatchesPlayed: Int = 0
    var Attendance: String = ""

    var golden_ball: String = ""
    var golden_boot: String = ""
    var golden_glove: String = ""
    var best_young_player: String = ""
}