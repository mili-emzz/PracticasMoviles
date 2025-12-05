package com.emiliagomez.intents_camera_app.views


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.emiliagomez.intents_camera_app.R

/***
 * Project: Recognition App
 * Package: com.danielflores.recognitionapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 16:15
 * All rights reserved 2025.
 **/

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CollectionGalleryView() {
    val context = LocalContext.current
    var imagesUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiplePhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 10)
    ) {
        imagesUris = it
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    multiplePhoto.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.photo), contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            FlowRow {
                imagesUris.forEach { uri ->
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(uri)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(130.dp)
                            .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                    )
                }
            }
        }
    }
}