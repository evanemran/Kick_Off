package com.evanemran.kickoff.models

import androidx.fragment.app.Fragment
import com.evanemran.kickoff.R
import com.evanemran.kickoff.fragments.*
import java.io.Serializable

enum class WorldCups(title: String, year: Int, image: String) : Serializable {
    RUSSIA("Russia", 2018, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i163b426ba7c61b78/version/1495737398/logo-2018-russia.png"),
    BRAZIL("Brazil", 2014, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/ib9fe644a4b09dc87/version/1490899255/logo-2014-brazil.png"),
    AFRICA("South Africa", 2010, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i85609e390290d511/version/1490899166/logo-2010-south-africa.png"),
    GERMANY("Germany", 2006, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i199fae8bf1beb68b/version/1491584332/logo-2006-germany.png"),
    KOREA_JAPAN("Korea Japan", 2002, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i890787870ad5172a/version/1490899231/logo-2002-korea-japan.png"),
    FRANCE("France", 1998, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/iedba2fc3a64ee6d3/version/1490899370/logo-1998-france.png"),
    USA("USA", 1994, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/ibc779e9ff8bcda44/version/1490899396/logo-1994-usa.png"),
    ITALY("Italy", 1990, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/if2609b3458e6f16a/version/1490899427/logo-1990-italy.png"),
    MEXICO("Mexico", 1986, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/idc975b1c00276ba3/version/1490899461/logo-1986-mexico.png"),
    ESPANA("Spain", 1982, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i5b1dd2a64709b225/version/1490899485/logo-1982-spain.png"),
    ARGENTINA("Argentina", 1978, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i1580681de9c13cb6/version/1490899506/logo-1978-argentina.png"),
    WEST_GERMANY("West Germany", 1974, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i5970cc7242071077/version/1490899531/logo-1974-west-germany.png"),
    MEXICO70("Mexico", 1970, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i66c7231cfd720efa/version/1491817126/logo-1970-mexico.png"),
    ENGLAND("England", 1966, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/ib9911250f010ecdb/version/1591724934/logo-1966-england.png"),
    CHILE("Chile", 1962, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/id50b342cd334a96e/version/1491415487/logo-1962-chile.png"),
    SWEDEN("Sweden", 1958, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/ic27652f650498fd9/version/1491415548/logo-1958-sweden.png"),
    SWITZERLAND("Switzerland", 1954, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i515d0166ec9093f3/version/1490899640/logo-1954-switzerland.png"),
    BRAZIL50("Brazil", 1950, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt1248d02c441b21be/626158076f5b784f80cfd06f/world-cup-logo-1950.jpg?quality=80&format=pjpg&auto=webp&width=620"),
    FRANCE38("France", 1938, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blted83949c5a94b12d/62615709bf6a365aa33f1822/world-cup-logo-1938.jpg?quality=80&format=pjpg&auto=webp&width=620"),
    ITALY34("Italy", 1934, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt80f8dafaf795f17a/62614e03956c474ead99e880/world-cup-logo-1934.jpg?quality=80&format=pjpg&auto=webp&width=620"),
    URUGUAY("Uruguay", 1930, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt4042526ed946279c/62614add356a0d4d8113cd4c/world-cup-logo-1930.jpg?quality=80&format=pjpg&auto=webp&width=620");

    var title: String = title
    var year: Int = year
    var image: String = image
}