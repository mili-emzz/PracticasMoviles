package com.emiliagomez.examenpractico.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.emiliagomez.examenpractico.viewmodels.FormsViewModel
import com.emiliagomez.examenpractico.views.DarkModeView
import com.emiliagomez.examenpractico.views.DashboardView
import com.emiliagomez.examenpractico.views.FormView

@Composable
fun NavManager(isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit, viewModel: FormsViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Dashboard" ){
        composable("Dashboard"){
            DashboardView(navController)
        }
        composable("Dark Mode") {
            DarkModeView(navController, isDarkTheme, onThemeChange)
        }
        composable("Formulario") {
            FormView(navController, viewModel)
        }
    }
}
