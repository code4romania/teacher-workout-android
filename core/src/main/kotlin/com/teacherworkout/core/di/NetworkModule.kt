package com.teacherworkout.core.di

import com.apollographql.apollo3.ApolloClient
import com.teacherworkout.core.data.repositories.LessonsRepository
import com.teacherworkout.core.data.repositories.impl.ApolloLessonsRepository
import org.koin.dsl.module

val networkModule = module {
    single {
        ApolloClient.Builder()
            .serverUrl("https://teacher.heroesof.tech/graphql")
            .build()
    }
    single<LessonsRepository>{
        ApolloLessonsRepository(get())
    }
}