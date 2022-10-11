package com.evanemran.kickoff.models

import com.evanemran.kickoff.R

enum class DrawerMenu(title: String, icon: Int) {
    STATS("Stats", R.drawable.ic_stats),
    HISTORY("History",R.drawable.ic_history),
    BLOGS("Blogs", R.drawable.ic_news),
    HELP_CENTER("Help center", R.drawable.ic_help),
    SETTINGS("Settings", R.drawable.ic_settings),
    TERMS("Privacy & Terms", R.drawable.ic_terms),
    LOGOUT("Log-out", R.drawable.ic_logout);

    var title: String = title
    var icon: Int = icon
}