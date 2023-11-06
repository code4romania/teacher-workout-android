package com.teacherworkout.features.profile.settings

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.commons.ui.theming.TeacherWorkoutTheme
import com.teacherworkout.features.profile.R
import com.teacherworkout.commons.ui.R as CommonRes


@Composable
fun SettingsTabContent(
    onNewPicture: (Uri) -> Unit
) {
    val settingsOptions = stringArrayResource(id = R.array.settings_options)
    val settingChangePhoto = stringResource(id = R.string.setting_change_photo)
    val settingChangePassword = stringResource(id = R.string.setting_change_password)
    val settingNotifications = stringResource(id = R.string.setting_notification)
    val settingLogout = stringResource(id = R.string.setting_logout)
    val settingDeleteAccount = stringResource(id = R.string.setting_delete_account)
    val space16dp = dimensionResource(id = CommonRes.dimen.space_16dp)

    var isUpdatePictureDialogOpen by remember { mutableStateOf(false) }
    var isDeleteAccountDialogOpen by remember { mutableStateOf(false) }

    AnimatedVisibility(isUpdatePictureDialogOpen) {
        UpdateProfilePictureDialog(
            onNewPicture = { onNewPicture(it) },
            onDismiss = { isUpdatePictureDialogOpen = false }
        )
    }

    AnimatedVisibility(isDeleteAccountDialogOpen) {
        DeleteAccountDialog(
            onAccountDelete = {},
            onDismiss = { isDeleteAccountDialogOpen = false }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(space16dp)
    ) {
        settingsOptions.forEach { entry ->
            item {
                SettingsRow(text = entry) { settingText ->
                    when (settingText) {
                        settingChangePhoto -> isUpdatePictureDialogOpen = true
                        settingChangePassword -> {
                            /* TODO */
                        }
                        settingNotifications -> {
                            /* TODO */
                        }
                        settingLogout -> {
                            /* TODO */
                        }
                        settingDeleteAccount -> isDeleteAccountDialogOpen = true
                        else -> error("Unknown setting selected: $settingText")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsTabContentPreview() {
    TeacherWorkoutTheme {
        SettingsTabContent(onNewPicture = {})
    }
}
