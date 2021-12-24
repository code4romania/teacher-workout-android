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

        /**
         * The learn feature
         */
        const val learn = "learn"


        /**
         * The profile feature
         */
        const val profile = "profile"

        const val lesson = "lesson"
    }

    object Account {
        abstract class AccountScreen(route : String) : Screen(route, R.string.title_account, Icons.Outlined.AccountCircle)

        object Landing : AccountScreen("account-landing")

        object Onboarding : AccountScreen("account-onboarding")

        object Registration : AccountScreen("account-registration")

        object Authentication : AccountScreen("account-authentication")

        object ResetPassword : AccountScreen("account-reset-password")

        object ResetPasswordSucceeded: AccountScreen("account-reset-password-succeeded")
    }

    object Home {
        object Landing : Screen("home-landing", R.string.title_home, Icons.Outlined.Home)
    }

    object Learn {
        object Discover : Screen("home-discover", R.string.title_discover, Icons.Outlined.Search)
    }

    object Profile {
        object Profile : Screen("home-profile", R.string.title_profile, Icons.Outlined.Person)
    }

    //TODO: update resourceId
    object Lesson {
        object Landing: Screen("home-lesson-landing/{lessonId}", -1, Icons.Outlined.Book)
    }
}
