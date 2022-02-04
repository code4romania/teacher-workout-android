package com.teacherworkout.features.lesson.di

import com.teacherworkout.features.lesson.LessonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {
    viewModel { (lessonId: String) -> LessonViewModel(lessonId, get()) }
}
