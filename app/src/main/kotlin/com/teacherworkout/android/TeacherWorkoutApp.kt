package com.teacherworkout.android

import com.google.android.play.core.splitcompat.SplitCompatApplication

class TeacherWorkoutApp : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        // TODO: init koin here
    }
}
