package com.teacherworkout.features.account.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teacherworkout.android.navigation.AppDestinations

@Composable
fun LandingScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        ApplicationLogo(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
        )
        RegistrationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            navController.navigate(AppDestinations.registration)
        }
        AuthenticationUi(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            navController.navigate(AppDestinations.authentication)
        }
    }
}