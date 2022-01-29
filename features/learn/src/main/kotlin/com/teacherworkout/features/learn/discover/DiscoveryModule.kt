package com.teacherworkout.features.learn.discover

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val discoveryModule = module {
    viewModel { DiscoverViewModel(FakeThemesRepository()) }
}
