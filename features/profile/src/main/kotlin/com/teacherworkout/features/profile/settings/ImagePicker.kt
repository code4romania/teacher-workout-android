package com.teacherworkout.features.profile.settings

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class ImagePicker(private val launcher: ManagedActivityResultLauncher<String, Uri>){
    fun launch() {
        launcher.launch("image/")
    }
}

@Composable
fun rememberImagePicker(onImageResult: (Uri) -> Unit): ImagePicker {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        if(it != null) onImageResult(it)
    }
    return remember { mutableStateOf(ImagePicker(launcher)) }.value
}
