package com.teacherworkout.features.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.profile.landing.Screen

fun NavGraphBuilder.profileFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Profile.Landing.route,
        route = AppDestinations.Features.profile
    ) {
        composable(route = AppDestinations.Profile.Landing.route) {
            BottomBarScaffold(navHostController) { Screen(navHostController) }
        }
    }
}
