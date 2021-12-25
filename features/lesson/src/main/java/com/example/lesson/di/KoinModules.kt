package com.example.lesson.di

import com.example.lesson.LessonViewModel
import com.teacherworkout.commons.ui.data.impl.FakeLessonsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {
    viewModel { (lessonId: Long) -> LessonViewModel(lessonId, FakeLessonsRepository()) }
}
