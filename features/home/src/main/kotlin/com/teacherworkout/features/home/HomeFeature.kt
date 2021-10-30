package com.teacherworkout.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.navigation.AppDestinations

fun NavGraphBuilder.homeFeature(_navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Home.Landing.route,
        route = AppDestinations.Features.home
    ) {
        composable(route = AppDestinations.Home.Landing.route) {
            LandingScreen()
        }
        composable(route = AppDestinations.Home.Discover.route) {
            DiscoverScreen()
        }
        composable(route = AppDestinations.Home.Profile.route) {
            ProfileScreen()
        }
    }
}
