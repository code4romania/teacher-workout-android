package com.teacherworkout.commons.ui.composables

import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.teacherworkout.commons.ui.navigation.AppDestinations

@Composable
fun BottomBarScaffold(
    navHostController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
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
            AppDestinations.Discover.Landing,
            AppDestinations.Profile.Landing
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
