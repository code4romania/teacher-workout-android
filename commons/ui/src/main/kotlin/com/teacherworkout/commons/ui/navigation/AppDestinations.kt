package com.teacherworkout.commons.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.teacherworkout.commons.ui.R

object AppDestinations {
    abstract class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector, val hasBottomNavigation: Boolean = true)

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
        val Screens = listOf(Landing, Discover, Profile)

        object Landing : Screen("home-landing", R.string.title_home, Icons.Outlined.Home)

        object Discover :
            Screen("home-discover", R.string.title_discover, Icons.Outlined.Search)

        object Profile :
            Screen("home-profile", R.string.title_profile, Icons.Outlined.Person)
    }

    object Account {
        abstract class AccountScreen(route : String) : Screen(route, R.string.title_account, Icons.Outlined.AccountCircle, hasBottomNavigation = false)

        val Screens = listOf(Landing, Registration, Authentication, ResetPassword)

        object Landing : AccountScreen("account-landing")

        object Registration : AccountScreen("account-registration")

        object Authentication : AccountScreen("account-authentication")

        object ResetPassword : AccountScreen("account-reset-password")
    }

    fun routeToScreen(route: String): Screen? {
        return (Home.Screens + Account.Screens).find { it.route == route }
    }
}
