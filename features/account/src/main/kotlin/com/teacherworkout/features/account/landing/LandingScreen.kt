package com.teacherworkout.features.account.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.commons.ui.R as CommonRes


@Composable
fun LandingScreen(navController: NavController) {
    val space16dp = dimensionResource(id =  CommonRes.dimen.space_16dp)
    val space24dp = dimensionResource(id =  CommonRes.dimen.space_24dp)
    val backgroundColor = colorResource(id =  CommonRes.color.landing_background)
    Box(modifier = Modifier.background(backgroundColor)) {
        Column(modifier = Modifier.padding(space16dp)) {
            ApplicationLogo(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
            )
            RegistrationButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = space16dp)
            ) {
                navController.navigate(AppDestinations.Account.Registration.route)
            }
            AuthenticationUi(
                modifier = Modifier
                    .padding(bottom = space24dp)
                    .fillMaxWidth()
            ) {
                navController.navigate(AppDestinations.Account.Authentication.route)
            }
        }
    }
}
