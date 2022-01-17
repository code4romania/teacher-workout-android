package com.teacherworkout.features.home.di

import com.teacherworkout.features.home.lessons.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}
