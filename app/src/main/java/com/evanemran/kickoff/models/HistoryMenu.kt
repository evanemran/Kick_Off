package com.evanemran.kickoff.models

import com.evanemran.kickoff.R

enum class HistoryMenu(title: String, icon: Int) {
    WINNERS("Winners", R.drawable.winner),
    WC_DETAILS("World Cup Stats",R.drawable.winner),
    GOLDEN_BALL("Golden Ball", R.drawable.winner),
    GOLDEN_BOOT("Golden Boot", R.drawable.winner),
    GOLDEN_GLOVE("Golden Glove", R.drawable.winner),
    YOUNG_PLAYER("Best Young Player", R.drawable.winner);

    var title: String = title
    var icon: Int = icon
}