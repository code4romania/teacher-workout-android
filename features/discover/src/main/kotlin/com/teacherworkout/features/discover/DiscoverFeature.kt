package com.teacherworkout.features.discover

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
import com.teacherworkout.features.discover.landing.Contract
import com.teacherworkout.features.discover.landing.Screen
import com.teacherworkout.features.discover.landing.ViewModel
import com.teacherworkout.features.discover.landing.landingModule
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules

fun NavGraphBuilder.discoverFeature(navHostController: NavHostController) {
    loadKoinModules(listOf(dataModule, landingModule))
    navigation(
        startDestination = AppDestinations.Discover.Landing.route,
        route = AppDestinations.Features.discover
    ) {
        composable(route = AppDestinations.Discover.Landing.route) {
            DiscoverScreenDestination(navHostController)
        }
    }
}

@Composable
private fun DiscoverScreenDestination(navHostController: NavHostController) {
    val landingViewModel: ViewModel = getViewModel()
    BottomBarScaffold(navHostController) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Screen(
                state = landingViewModel.viewState.value,
                onSendEvent = { event -> landingViewModel.setEvent(event) },
                effects = landingViewModel.effect,
                onNavigationRequest = { navigationEffect: Contract.Effect.Navigation ->
                    when(navigationEffect) {
                        is Contract.Effect.Navigation.ToLessonThemeDetails -> {
                            //val lessonThemeName = navigationEffect.lessonThemeName
                        }
                    }
                }
            )
        }
    }
}
