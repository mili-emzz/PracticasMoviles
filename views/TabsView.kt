package com.emiliagomez.intents_camera_app.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emiliagomez.intents_camera_app.viewmodels.ScannerViewModel

/***
 * Project: Recognition App
 * Package: com.danielflores.recognitionapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 16:16
 * All rights reserved 2025.
 **/

@Composable
fun TabsView(viewModel: ScannerViewModel){
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Gallery", "Camera", "Collection")
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                contentColor = Color.Black,
                indicator = { position ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(position[selectedTab]),
                        2.dp, Color.Black
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(tabs[index]) }
                    )
                }
            }
            when(selectedTab){
                0 -> GalleryView(viewModel).apply { viewModel.cleanText() }
                1 -> CameraView(viewModel).apply { viewModel.cleanText() }
                2 -> CollectionGalleryView()
            }
        }
    }
}