package com.teacherworkout.features.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.learn.di.learnModule
import com.teacherworkout.features.learn.discover.DiscoverScreen
import com.teacherworkout.features.learn.discover.DiscoverViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules

fun NavGraphBuilder.learnFeature(navHostController: NavHostController) {
    loadKoinModules(learnModule)
    navigation(
        startDestination = AppDestinations.Learn.Discover.route,
        route = AppDestinations.Features.learn
    ) {
        composable(route = AppDestinations.Learn.Discover.route) {
            DiscoverScreenDestination(navHostController)
        }
    }
}

@Composable
private fun DiscoverScreenDestination(navHostController: NavHostController) {
    val viewModel: DiscoverViewModel = getViewModel()
    BottomBarScaffold(navHostController) { innerPadding ->
        Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
            DiscoverScreen(
                viewModel.viewState.value,
                { event -> viewModel.setEvent(event)},
                navHostController
            )
        }
    }
}
