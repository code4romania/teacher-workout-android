package com.teacherworkout.features.learn

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations

fun NavGraphBuilder.learnFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Learn.Landing.route,
        route = AppDestinations.Features.learn
    ) {
        composable(route = AppDestinations.Learn.Landing.route) {
            BottomBarScaffold(navHostController) { LandingScreen() }
        }
        composable(route = AppDestinations.Learn.Discover.route) {
            BottomBarScaffold(navHostController) { DiscoverScreen() }
        }
    }
}
