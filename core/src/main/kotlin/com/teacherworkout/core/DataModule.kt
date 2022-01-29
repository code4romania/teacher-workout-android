package com.teacherworkout.core

import org.koin.dsl.module
import com.apollographql.apollo3.ApolloClient

val dataModule = module {
    single {
        ApolloClient.Builder()
            .serverUrl("https://teacher.heroesof.tech/graphql")
            .build()
    }
}
