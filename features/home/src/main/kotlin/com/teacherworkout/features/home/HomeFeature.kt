package com.teacherworkout.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.navigation.AppDestinations

fun NavGraphBuilder.homeFeature(_navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Home.landing,
        route = AppDestinations.Features.home
    ) {
        composable(route = AppDestinations.Home.landing) {
            LandingScreen()
        }
        composable(route = AppDestinations.Home.profile) {
            ProfileScreen()
        }
    }
}
