package com.evanemran.kickoff.models

import androidx.fragment.app.Fragment
import com.evanemran.kickoff.R
import com.evanemran.kickoff.fragments.*
import java.io.Serializable

enum class WorldCups(title: String, year: Int, image: String) : Serializable {
    RUSSIA("Russia", 2018, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltf027973065732a87/6261b7b9700cd1583cc710d8/world-cup-2018.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    BRAZIL("Brazil", 2014, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt5e47758259f72bc7/6261b77847786b5e6be40522/world-cup-2014.png?quality=80&format=pjpg&auto=webp&width=1000"),
    AFRICA("South Africa", 2010, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltf453bdf5054585c0/6261b73306f43e5c025625a1/world-cup-2010.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    GERMANY("Germany", 2006, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blta407e38ffd5923b2/6261b6cb06f43e5c0256259d/world-cup-2006.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    KOREA_JAPAN("Korea Japan", 2002, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blte602854efb03177f/6261b67c9b4e3056e414b10c/world-cup-2002.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    FRANCE("France", 1998, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltd3b33c5fd673b48f/6261b64aa5ff555bf767973a/world-cup-1998.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    USA("USA", 1994, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt34a72a5e079d37f3/6261b61d700cd1583cc710d4/world-cup-1994.png?quality=80&format=pjpg&auto=webp&width=1000"),
    ITALY("Italy", 1990, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt5ca6fae933a635cc/6261b5d9700cd1583cc710d0/world-cup-1990.png?quality=80&format=pjpg&auto=webp&width=1000"),
    MEXICO("Mexico", 1986, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltb0f6b0c5e3c3f684/6261b5aae5224e5d21274428/world-cup-1986.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    ESPANA("Spain", 1982, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt319b5ce19c8a3cd6/6261b56290538e5d2d560025/world-cup-1982.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    ARGENTINA("Argentina", 1978, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt7a9130a2aaa91c91/6261b510a5ff555bf7679736/world-cup-logo-1978.png?quality=80&format=pjpg&auto=webp&width=1000"),
    WEST_GERMANY("West Germany", 1974, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt58fc268c65622f17/6261b4c3700cd1583cc710cc/world-cup-logo-1974.png?quality=80&format=pjpg&auto=webp&width=1000"),
    MEXICO70("Mexico", 1970, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltc838160c57e73933/6261b48447786b5e6be4051e/world-cup-logo-1970.png?quality=80&format=pjpg&auto=webp&width=1000"),
    ENGLAND("England", 1966, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltdfc0c51a524794b8/6261b44cc0d80d5a984d2ede/world-cup-logo-1966.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    CHILE("Chile", 1962, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltd2dbf6d4013cd086/6261b381d2e0254bc5a37de7/world-cup-logo-1962-1.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    SWEDEN("Sweden", 1958, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt7df594ce8843adb5/626158e5638d944cfb0c10c0/world-cup-logo-1958.jpg?quality=80&format=pjpg&auto=webp&width=1000"),
    SWITZERLAND("Switzerland", 1954, "https://image.jimcdn.com/app/cms/image/transf/dimension=211x10000:format=png/path/s6d54c624f88c95ec/image/i515d0166ec9093f3/version/1490899640/logo-1954-switzerland.png"),
    BRAZIL50("Brazil", 1950, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt1248d02c441b21be/626158076f5b784f80cfd06f/world-cup-logo-1950.jpg?quality=80&format=pjpg&auto=webp&width=620"),
    FRANCE38("France", 1938, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blted83949c5a94b12d/62615709bf6a365aa33f1822/world-cup-logo-1938.jpg?quality=80&format=pjpg&auto=webp&width=620"),
    ITALY34("Italy", 1934, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt80f8dafaf795f17a/62614e03956c474ead99e880/world-cup-logo-1934.jpg?quality=80&format=pjpg&auto=webp&width=620"),
    URUGUAY("Uruguay", 1930, "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt4042526ed946279c/62614add356a0d4d8113cd4c/world-cup-logo-1930.jpg?quality=80&format=pjpg&auto=webp&width=620");

    var title: String = title
    var year: Int = year
    var image: String = image
}