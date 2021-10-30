package com.teacherworkout.commons.ui.navigation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AppDestinationsTest {
    @Test
    fun `routeToScreen will return Home Landing screen`() {
        assertThat(AppDestinations.routeToScreen("home-landing")).isEqualTo(AppDestinations.Home.Landing)
    }

    @Test
    fun `routeToScreen will return Home Discover screen`() {
        assertThat(AppDestinations.routeToScreen("home-discover")).isEqualTo(AppDestinations.Home.Discover)
    }

    @Test
    fun `routeToScreen will return Home Profile screen`() {
        assertThat(AppDestinations.routeToScreen("home-profile")).isEqualTo(AppDestinations.Home.Profile)
    }
}
