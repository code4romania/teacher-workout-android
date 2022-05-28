package com.teacherworkout.features.discover.landing

import com.apollographql.apollo3.ApolloClient
import com.teacherworkout.discover.dtos.ThemesQuery

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
                Theme(id = this?.id ?: "", title = this?.title ?: "")
            }
        }
}
