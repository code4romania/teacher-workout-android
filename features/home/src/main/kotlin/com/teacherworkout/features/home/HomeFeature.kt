package com.teacherworkout.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.learn.learnFeature

fun NavGraphBuilder.homeFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Learn.Landing.route,
        route = AppDestinations.Features.home
    ) {
        learnFeature(navHostController)
        composable(route = AppDestinations.Home.Profile.route) {
            BottomBarScaffold(navHostController) { ProfileScreen() }
        }
    }
}
