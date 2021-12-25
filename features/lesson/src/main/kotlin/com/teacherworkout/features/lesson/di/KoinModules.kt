package com.teacherworkout.features.lesson.di

import com.teacherworkout.features.lesson.LessonViewModel
import com.teacherworkout.commons.ui.data.impl.FakeLessonsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {
    viewModel { (lessonId: Long) -> LessonViewModel(lessonId, FakeLessonsRepository()) }
}
