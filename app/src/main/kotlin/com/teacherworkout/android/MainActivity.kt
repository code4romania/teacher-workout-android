package com.teacherworkout.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.commons.ui.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.accountFeature
import com.teacherworkout.features.home.homeFeature

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()

            TeacherWorkoutTheme {
                Scaffold(
                    bottomBar = { BottomBar(navBackStackEntry, navHostController) }
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

    @Composable
    fun BottomBar(navBackStackEntry: NavBackStackEntry?, navHostController: NavHostController) {
        if (navBackStackEntry == null) {
            return
        }

        val currentRoute = navBackStackEntry.destination.route
        if (AppDestinations.routeToScreen(currentRoute ?: "")?.hasBottomNavigation == false) {
            return
        }

        BottomNavigation {
            val items = listOf(
                AppDestinations.Home.Landing,
                AppDestinations.Home.Discover,
                AppDestinations.Home.Profile
            )
            val currentDestination = navBackStackEntry.destination

            items.forEach { screen ->
                BottomBarItem(screen, currentDestination, navHostController)
            }
        }
    }

    @Composable
    private fun RowScope.BottomBarItem(
        screen: AppDestinations.Screen,
        currentDestination: NavDestination,
        navHostController: NavHostController
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    screen.icon,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(screen.resourceId)) },
            selected = currentDestination.hierarchy.any { it.route == screen.route },
            onClick = {
                navHostController.navigate(screen.route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
