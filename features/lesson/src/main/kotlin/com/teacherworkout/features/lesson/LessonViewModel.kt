package com.teacherworkout.features.lesson

import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.core.data.repositories.LessonsRepository
import com.teacherworkout.core.data.Result
import kotlinx.coroutines.launch

//TODO: properly implement the LessonViewModel
class LessonViewModel(
    private val lessonId: String,
    private val lessonsRepository: LessonsRepository
): BaseViewModel<LessonContract.Event, LessonContract.State, LessonContract.Effect>() {
    override fun setInitialState(): LessonContract.State {
        return LessonContract.State()
    }
    override fun handleEvents(event: LessonContract.Event) {
        when(event) {
            is LessonContract.Event.StartContinue -> { startContinue() }
            is LessonContract.Event.Save -> { save() }
            is LessonContract.Event.Unsave -> { unsave() }
            is LessonContract.Event.NavigateUp -> { navigateUp() }
        }
    }

    init {
        viewModelScope.launch {
            when(val result = lessonsRepository.getLesson(lessonId)) {
                is Result.Success -> {
                    val lesson = result.data
                    setState { copy(lesson = lesson) }
                }
                is Result.Error -> {

                }
            }
        }
    }

    private fun startContinue() {

    }

    private fun save() {

    }

    private fun unsave() {

    }

    private fun navigateUp() {
        setEffect { LessonContract.Effect.Navigation.NavigateUp }
    }
}
