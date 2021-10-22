package com.teacherworkout.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.commons.ui.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.accountFeature
import com.teacherworkout.features.home.homeFeature

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.title_home, Icons.Outlined.Home)
    object Discover : Screen("discover", R.string.title_discover, Icons.Outlined.Search)
    object Profile : Screen("profile", R.string.title_profile, Icons.Outlined.Person)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val items = listOf(
                Screen.Home,
                Screen.Discover,
                Screen.Profile
            )

            TeacherWorkoutTheme {
                Scaffold(
                    bottomBar = {
                        if (currentRoute == "home-landing") {
                            BottomNavigation {
                                val currentDestination = navBackStackEntry?.destination
                                items.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                screen.icon,
                                                contentDescription = null
                                            )
                                        },
                                        label = { Text(stringResource(screen.resourceId)) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navHostController.navigate(screen.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items
                                                popUpTo(navHostController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true
                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) {
                    // TODO: startDestination will probably need to be dynamic depending if the user is logged or not
                    NavHost(
                        navController = navHostController,
                        startDestination = AppDestinations.Features.account
                    ) {
                        accountFeature(navHostController)
                        homeFeature(navHostController)
                    }
                }
            }
        }
    }
}