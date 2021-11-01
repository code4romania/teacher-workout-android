package com.teacherworkout.features.learn

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations

fun NavGraphBuilder.learnFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Learn.Discover.route,
        route = AppDestinations.Features.learn
    ) {
        composable(route = AppDestinations.Learn.Discover.route) {
            BottomBarScaffold(navHostController) { DiscoverScreen() }
        }
    }
}
