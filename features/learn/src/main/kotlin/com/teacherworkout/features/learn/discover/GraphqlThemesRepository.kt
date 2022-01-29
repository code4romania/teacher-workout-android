package com.teacherworkout.features.learn.discover

import com.apollographql.apollo3.ApolloClient
import com.teacherworkout.learn.dtos.ThemesQuery

class GraphqlThemesRepository(private val client: ApolloClient) : ThemesRepository {
    override suspend fun all(): Result<List<Theme>> {
        val response = client.query(ThemesQuery()).execute()

        return if (response.hasErrors()) {
            Result.failure(Exception(response.errors?.first()?.message ?: "unknown error"))
        } else {
            Result.success(response.data?.themes?.edges?.toThemes() ?: emptyList())
        }
    }

    fun List<ThemesQuery.Edge?>.toThemes(): List<Theme> =
        this.map {
            with(it?.node) {
                Theme(id = 1, title = this?.title ?: "")
            }
        }
}
