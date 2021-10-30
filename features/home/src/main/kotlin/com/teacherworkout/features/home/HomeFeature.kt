package com.teacherworkout.features.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.teacherworkout.commons.ui.navigation.AppDestinations

fun NavGraphBuilder.homeFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Home.Landing.route,
        route = AppDestinations.Features.home
    ) {
        composable(route = AppDestinations.Home.Landing.route) {
            HomeScaffold(navHostController) { LandingScreen() }
        }
        composable(route = AppDestinations.Home.Discover.route) {
            HomeScaffold(navHostController) { DiscoverScreen() }
        }
        composable(route = AppDestinations.Home.Profile.route) {
            HomeScaffold(navHostController) { ProfileScreen() }
        }
    }
}

@Composable
fun HomeScaffold(navHostController: NavHostController, content: @Composable (PaddingValues) -> Unit) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = { BottomBar(navBackStackEntry, navHostController) },
        content = content
    )
}

@Composable
fun BottomBar(navBackStackEntry: NavBackStackEntry?, navHostController: NavHostController) {
    if (navBackStackEntry == null) {
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

