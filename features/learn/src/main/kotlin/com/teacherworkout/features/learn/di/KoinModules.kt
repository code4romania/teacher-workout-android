package com.teacherworkout.features.learn.di

import com.teacherworkout.features.learn.discover.discoveryModule
import org.koin.dsl.module

val learnModule = module {
    discoveryModule
}
