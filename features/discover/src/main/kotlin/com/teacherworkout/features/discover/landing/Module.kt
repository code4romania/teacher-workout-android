package com.teacherworkout.features.discover.landing

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val landingModule = module {
    viewModel { ViewModel(GraphqlThemesRepository(get())) }
}
