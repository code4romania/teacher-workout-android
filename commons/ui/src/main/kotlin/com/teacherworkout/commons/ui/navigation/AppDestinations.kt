package com.teacherworkout.commons.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Book
import androidx.compose.ui.graphics.vector.ImageVector
import com.teacherworkout.commons.ui.R

object AppDestinations {
    open class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector)

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
         * The discover feature
         */
        const val discover = "discover"

        /**
         * The profile feature
         */
        const val profile = "profile"

        /**
         * The lesson feature
         */
        const val lesson = "lesson"
    }

    object Account {
        abstract class AccountScreen(route: String) :
            Screen(route, R.string.title_account, Icons.Outlined.AccountCircle)

        object Landing : AccountScreen("account-landing")

        object Onboarding : AccountScreen("account-onboarding")

        object Registration : AccountScreen("account-registration")

        object Authentication : AccountScreen("account-authentication")

        object ResetPassword : AccountScreen("account-reset-password")

        object ResetPasswordSucceeded : AccountScreen("account-reset-password-succeeded")
    }

    object Home {
        object Landing : Screen("home-landing", R.string.title_home, Icons.Outlined.Home)
    }

    object Discover {
        object Landing : Screen("discover-landing", R.string.title_discover, Icons.Outlined.Search)
    }

    object Profile {
        object Landing : Screen("profile-landing", R.string.title_profile, Icons.Outlined.Person)
    }

    object Lesson {
        object Landing : Screen("lesson-landing/{lessonId}", -1, Icons.Outlined.Book)
    }
}
