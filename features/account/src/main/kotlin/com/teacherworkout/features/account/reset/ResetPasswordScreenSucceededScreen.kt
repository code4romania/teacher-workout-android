package com.teacherworkout.features.account.reset

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.AccountScreenScaffold

@Composable
fun ResetPasswordSucceededScreen(navHostController: NavHostController) {
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)
    AccountScreenScaffold(titleId = R.string.reset_password_succeeded_title, navController = navHostController) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = space16dp)
        ) {
            Image(
                colorFilter = ColorFilter.tint(colorResource(id = com.teacherworkout.commons.ui.R.color.colorPrimary)),
                painter = painterResource(id = com.teacherworkout.commons.ui.R.drawable.ic_email),
                contentDescription = stringResource(id = R.string.reset_password_cd_email)
            )
            Spacer(modifier = Modifier.width(space16dp))
            Text(text = stringResource(id = R.string.reset_password_success_label))
        }
    }
}
