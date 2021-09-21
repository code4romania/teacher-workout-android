package com.teacherworkout.features.profile.settings

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.profile.R

@Composable
fun SettingsTabContent() {
    val settingsOptions = stringArrayResource(id = R.array.settings_options)
    val settingChangePhoto = stringResource(id = R.string.setting_change_photo)
    val settingChangePassword = stringResource(id = R.string.setting_change_password)
    val settingNotifications = stringResource(id = R.string.setting_notification)
    val settingLogout = stringResource(id = R.string.setting_logout)
    val settingDeleteAccount = stringResource(id = R.string.setting_delete_account)
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(space16dp)
    ) {
        settingsOptions.forEach { entry ->
            item {
                SettingsRow(text = entry) { settingText ->
                    when (settingText) {
                        settingChangePhoto -> {
                            /* TODO */
                        }
                        settingChangePassword -> {
                            /* TODO */
                        }
                        settingNotifications -> {
                            /* TODO */
                        }
                        settingLogout -> {
                            /* TODO */
                        }
                        settingDeleteAccount -> {
                            /* TODO */
                        }
                        else -> error("Unknown setting selected: $settingText")
                    }
                }
            }
        }
    }
}
