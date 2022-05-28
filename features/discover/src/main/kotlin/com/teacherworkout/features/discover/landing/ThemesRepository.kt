package com.teacherworkout.features.discover.landing

interface ThemesRepository {
    suspend fun all(): Result<List<Theme>>
}
