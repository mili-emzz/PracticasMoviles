package com.emiliagomez.practica2.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emiliagomez.practica2.views.CoroutinesView
import com.emiliagomez.practica2.views.DashboardView

@Composable
fun NavManager(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Dashboard" ){
         composable("Dashboard"){
             DashboardView(navController)
         }
        composable("Coroutines") {
            CoroutinesView(navController = navController)
        }
    }
}