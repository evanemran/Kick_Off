package com.evanemran.kickoff.models

import androidx.fragment.app.Fragment
import com.evanemran.kickoff.R
import com.evanemran.kickoff.fragments.*
import java.io.Serializable

enum class HistoryMenu(title: String, icon: Int, fragment: Fragment) : Serializable {
    WINNERS("Winners", R.drawable.app_banner, WinnersFragment()),
    WC_DETAILS("World Cup Stats",R.drawable.app_banner, WcStatsFragment()),
    GOLDEN_BALL("Golden Ball", R.drawable.app_banner, GoldenBallFragment()),
    GOLDEN_BOOT("Golden Boot", R.drawable.app_banner, GoldenBootFragment()),
    GOLDEN_GLOVE("Golden Glove", R.drawable.app_banner, GoldenGloveFragment()),
    YOUNG_PLAYER("Best Young Player", R.drawable.app_banner, YoungPlayerFragment());

    var title: String = title
    var icon: Int = icon
    var fragment: Fragment = fragment
}