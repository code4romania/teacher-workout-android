package com.teacherworkout.features.home.lessons

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.core.data.repositories.LessonsRepository
import com.teacherworkout.core.data.Result
import com.teacherworkout.core.fragment.LessonStatus
import com.teacherworkout.core.fragment.LessonTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(
    private val lessonsRepository: LessonsRepository
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    private var allLessonStatuses: List<LessonStatus> = emptyList()
    private var allLessonThemes: List<LessonTheme> = emptyList()

    override fun setInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is HomeContract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonThemeId)
            is HomeContract.Event.SelectLesson -> selectLesson(event.lessonId)
        }
    }

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            lateinit var lessonThemesResult: Result<List<LessonTheme>>
            lateinit var lessonStatusesResult: Result<List<LessonStatus>>
            awaitAll(
                async {
                    lessonThemesResult = lessonsRepository.getLessonThemes()
                },
                async {
                    lessonStatusesResult = lessonsRepository.getLessonStatuses(null)
                }
            )
            setState { copy(isLoading = false) }
            if(lessonStatusesResult is Result.Success && lessonThemesResult is Result.Success) {
                allLessonStatuses = (lessonStatusesResult as Result.Success<List<LessonStatus>>).data
                allLessonThemes = (lessonThemesResult as Result.Success<List<LessonTheme>>).data
                setState {
                    copy(
                        searchInput = TextFieldValue(),
                        lessonStatuses = allLessonStatuses,
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
            HomeContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeId)
        }
    }

    private fun selectLesson(lessonId: String) {
        setEffect {
            HomeContract.Effect.Navigation.ToLessonDetails(lessonId)
        }
    }

    //TODO: change the method to filter through lessons. The UI part also needs to be modified to handle it
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
