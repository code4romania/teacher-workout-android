package com.teacherworkout.commons.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.teacherworkout.commons.ui.R

object AppDestinations {
    abstract class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector)

    object Features {
        /**
         * The account feature
         */
        const val account = "account"

        /**
         * The home feature
         */
        const val home = "home"
    }

    object Home {
        object Landing : Screen("home-landing", R.string.title_home, Icons.Outlined.Home)

        object Discover :
            Screen("home-discover", R.string.title_discover, Icons.Outlined.Search)

        object Profile :
            Screen("home-profile", R.string.title_profile, Icons.Outlined.Person)
    }

    object Account {
        abstract class AccountScreen(route : String) : Screen(route, R.string.title_account, Icons.Outlined.AccountCircle)

        object Landing : AccountScreen("account-landing")

        object Registration : AccountScreen("account-registration")

        object Authentication : AccountScreen("account-authentication")

        object ResetPassword : AccountScreen("account-reset-password")
    }
}
