package com.teacherworkout.features.learn.di

import com.teacherworkout.features.learn.data.impl.FakeLessonsRepository
import com.teacherworkout.features.learn.discover.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val learnModule = module {
    viewModel { DiscoverViewModel(FakeLessonsRepository()) }
}
