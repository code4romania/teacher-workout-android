package com.teacherworkout.android

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.teacherworkout.features.account.di.accountModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TeacherWorkoutApp : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidLogger()
            //inject Android context
            androidContext(this@TeacherWorkoutApp)
        }
    }
}
