package com.emiliagomez.cuartitosa_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emiliagomez.cuartitosa_app.presentation.views.AddView
import com.emiliagomez.cuartitosa_app.presentation.views.DashboardView
import com.emiliagomez.cuartitosa_app.presentation.views.DetailsViews

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ){
        composable("Dashboard"){ //este composable viene de la libreria y "esta ruta tendra este elemtno visual
            DashboardView(navController = navController)
        }

        composable("AddView"){  // 👈 Nueva ruta
            AddView(
                navController = navController
            )
        }
        composable("Details/{id}", arguments = listOf(
            navArgument(name = "id"){
                type = NavType.LongType
            }
        )){
            val id = it.arguments?.getLong("id") ?: 0L
            DetailsViews(navController, id)
        }
    }
}



