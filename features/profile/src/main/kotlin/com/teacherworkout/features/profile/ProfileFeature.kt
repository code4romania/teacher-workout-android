package com.teacherworkout.features.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations

fun NavGraphBuilder.profileFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Profile.Profile.route,
        route = AppDestinations.Features.profile
    ) {
        composable(route = AppDestinations.Profile.Profile.route) {
            BottomBarScaffold(navHostController) { ProfileScreen() }
        }
    }
}
