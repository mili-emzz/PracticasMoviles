package com.emiliagomez.cuartitosa_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.emiliagomez.cuartitosa_app.presentation.navigation.NavManager
import com.emiliagomez.cuartitosa_app.presentation.views.DashboardView
import com.emiliagomez.cuartitosa_app.ui.theme.CuartitosA_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CuartitosA_appTheme {
                NavManager()
                }
            }
        }
    }
