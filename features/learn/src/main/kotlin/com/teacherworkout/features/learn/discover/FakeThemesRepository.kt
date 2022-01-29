package com.teacherworkout.features.learn.discover

class FakeThemesRepository : ThemesRepository {
    override suspend fun all(): Result<List<Theme>> {
        TODO("Not yet implemented")
    }
}
