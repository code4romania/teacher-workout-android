package com.teacherworkout.features.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.core.dataModule
import com.teacherworkout.features.learn.discover.DiscoverContract
import com.teacherworkout.features.learn.discover.DiscoverScreen
import com.teacherworkout.features.learn.discover.DiscoverViewModel
import com.teacherworkout.features.learn.discover.discoveryModule
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules

fun NavGraphBuilder.learnFeature(navHostController: NavHostController) {
    loadKoinModules(listOf(dataModule, discoveryModule))
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
        Box(modifier = Modifier.padding(innerPadding)) {
            DiscoverScreen(
                state = viewModel.viewState.value,
                onSendEvent = { event -> viewModel.setEvent(event) },
                effects = viewModel.effect,
                onNavigationRequest = { navigationEffect: DiscoverContract.Effect.Navigation ->
                    when(navigationEffect) {
                        is DiscoverContract.Effect.Navigation.ToLessonThemeDetails -> {
                            //val lessonThemeName = navigationEffect.lessonThemeName
                        }
                    }
                }
            )
        }
    }
}
