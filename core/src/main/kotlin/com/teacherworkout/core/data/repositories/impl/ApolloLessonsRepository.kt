package com.teacherworkout.core.data.repositories.impl

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.Query
import com.teacherworkout.core.LessonEntityQuery
import com.teacherworkout.core.LessonIdListQuery
import com.teacherworkout.core.LessonStatusListQuery
import com.teacherworkout.core.ThemeListQuery
import com.teacherworkout.core.data.ErrorEntity
import com.teacherworkout.core.data.repositories.LessonsRepository
import com.teacherworkout.core.data.Result
import com.teacherworkout.core.fragment.Lesson
import com.teacherworkout.core.fragment.LessonStatus
import com.teacherworkout.core.fragment.LessonTheme

class ApolloLessonsRepository(private val apolloClient: ApolloClient): LessonsRepository {

    private suspend fun <D: Query.Data, T: Any> executeQuery(
        query: Query<D>,
        queryDataToResult: (D) -> T
    ): Result<T> =
        try {
            val response = apolloClient.query(query).execute()
            if(response.hasErrors() || response.data == null) {
                //TODO: map the apollo error to the proper ErrorEntity (e.g. code 404 -> ApiError.NotFound)
                Result.Error(ErrorEntity.ApiError.ServerError)
            } else {
                Result.Success(queryDataToResult(response.data!!))
            }
        } catch (e: Exception) {
            //TODO: map the exception to the proper ErrorEntity
            Result.Error(ErrorEntity.ApiError.ServerError)
        }

    override suspend fun getLessonThemes(): Result<List<LessonTheme>> =
        executeQuery(ThemeListQuery()) { themesData ->
            themesData.themes!!.items!!
        }

    override suspend fun getLessonStatuses(lessonThemeId: String?): Result<List<LessonStatus>> {
        val lessonIdListResponse = executeQuery(LessonIdListQuery(Optional.presentIfNotNull(lessonThemeId))) {
            it.lessons!!.items!!.map { item -> item.id!! }
        }
        return when(lessonIdListResponse) {
            is Result.Success -> {
                executeQuery(LessonStatusListQuery(lessonIdListResponse.data)) {
                    it.lessonStatuses!!
                }
            }
            is Result.Error -> {
                lessonIdListResponse
            }
        }
    }

    override suspend fun getLessonStatus(id: String): Result<LessonStatus> =
        executeQuery(LessonStatusListQuery(listOf(id))) {
            it.lessonStatuses!!.single()
        }

    override suspend fun getLesson(id: String): Result<Lesson> =
        executeQuery(LessonEntityQuery(id)) {
            it.lessonStatuses!!.single().lesson
        }
}
