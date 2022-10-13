package com.evanemran.kickoff.models

import androidx.fragment.app.Fragment
import com.evanemran.kickoff.R
import com.evanemran.kickoff.fragments.*
import java.io.Serializable

enum class HistoryMenu(title: String, icon: Int, fragment: Fragment) : Serializable {
    WINNERS("Winners", R.drawable.winner, WinnersFragment()),
    WC_DETAILS("World Cup Stats",R.drawable.winner, WcStatsFragment()),
    GOLDEN_BALL("Golden Ball", R.drawable.winner, GoldenBallFragment()),
    GOLDEN_BOOT("Golden Boot", R.drawable.winner, GoldenBootFragment()),
    GOLDEN_GLOVE("Golden Glove", R.drawable.winner, GoldenGloveFragment()),
    YOUNG_PLAYER("Best Young Player", R.drawable.winner, YoungPlayerFragment());

    var title: String = title
    var icon: Int = icon
    var fragment: Fragment = fragment
}