package com.teacherworkout.features.home.di

import com.teacherworkout.commons.ui.data.impl.FakeLessonsRepository
import com.teacherworkout.features.home.landing.LandingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { LandingViewModel(FakeLessonsRepository()) }
}
