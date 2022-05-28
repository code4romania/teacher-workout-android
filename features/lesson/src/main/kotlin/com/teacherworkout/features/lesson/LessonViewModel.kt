package com.teacherworkout.features.lesson

import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.data.Result
import kotlinx.coroutines.launch

class LessonViewModel(
    private val lessonId: Long,
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
        val lesson = viewState.value.lesson
        setState { copy(lesson = lesson?.copy(started = !lesson.started)) }
    }

    private fun save() {
        val lesson = viewState.value.lesson
        setState { copy(lesson = lesson?.copy(saved = true)) }
    }

    private fun unsave() {
        val lesson = viewState.value.lesson
        setState { copy(lesson = lesson?.copy(saved = false)) }
    }

    private fun navigateUp() {
        setEffect { LessonContract.Effect.Navigation.NavigateUp }
    }
}
