package com.teacherworkout.features.learn.discover

interface ThemesRepository {
    suspend fun all(): Result<List<Theme>>
}
