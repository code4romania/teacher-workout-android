package com.teacherworkout.features.home.landing

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.data.Result
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.commons.ui.model.LessonTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.random.Random

class LandingViewModel(
    private val lessonsRepository: LessonsRepository
) : BaseViewModel<LandingContract.Event, LandingContract.State, LandingContract.Effect>() {

    private var allLessons: List<Lesson> = emptyList()
    private var allLessonThemes: List<LessonTheme> = emptyList()

    override fun setInitialState(): LandingContract.State = LandingContract.State()

    override fun handleEvents(event: LandingContract.Event) {
        when (event) {
            is LandingContract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is LandingContract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonThemeId)
            is LandingContract.Event.SelectLesson -> selectLesson(event.lessonId)
        }
    }

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            lateinit var lessonThemeResult: Result<List<LessonTheme>>
            lateinit var lessonsResult: Result<List<Lesson>>
            awaitAll(
                async {
                    lessonThemeResult = lessonsRepository.getAllLessonThemes()
                },
                async {
                    lessonsResult = lessonsRepository.getAllLessons()
                }
            )
            setState { copy(isLoading = false) }
            if(lessonsResult is Result.Success && lessonThemeResult is Result.Success) {
                allLessons = (lessonsResult as Result.Success<List<Lesson>>).data
                allLessonThemes = (lessonThemeResult as Result.Success<List<LessonTheme>>).data
                setState {
                    copy(
                        searchInput = TextFieldValue(),
                        lessons = allLessons,
                        lessonThemes = allLessonThemes
                    )
                }
            }
        }
    }

    private fun setSearchInput(searchInput: TextFieldValue) {
        val prevSearchInput = viewState.value.searchInput
        setState { copy(searchInput = searchInput) }
        if (prevSearchInput.text != searchInput.text) {
            applyRandomFilter(searchInput.text)
        }
    }

    private fun selectLessonTheme(lessonThemeId: String) {
        setEffect {
            LandingContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeId)
        }
    }

    private fun selectLesson(lessonId: Long) {
        setEffect {
            LandingContract.Effect.Navigation.ToLessonDetails(lessonId)
        }
    }

    private fun applyRandomFilter(searchInput: String) {
        if (searchInput.isBlank()) {
            setState { copy(lessonThemes = allLessonThemes) }
        } else {
            val randomNumberOfLessonThemes = Random.nextInt(1, allLessonThemes.size)
            val someLessonThemes = mutableListOf<LessonTheme>()
            repeat(randomNumberOfLessonThemes) {
                someLessonThemes.add(allLessonThemes[Random.nextInt(0, allLessonThemes.size)])
            }
            setState { copy(lessonThemes = someLessonThemes) }
        }
    }
}
