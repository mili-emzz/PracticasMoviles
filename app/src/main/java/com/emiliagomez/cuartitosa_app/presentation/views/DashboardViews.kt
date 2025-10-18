package com.emiliagomez.cuartitosa_app.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.emiliagomez.cuartitosa_app.data.Student
import com.emiliagomez.cuartitosa_app.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavHostController, viewModel: StudentViewModel = viewModel()){

    val studentList by viewModel.students.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title= {
                    Text("Dashboard")
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("AddView") }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    "Agregar Estudiantes"
                )
            }
        }
    ){
        Content(it,studentList, navController)

    }
}

@Composable
fun Content(paddingValues: PaddingValues, student: List<Student>, navController: NavHostController){
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ){
        items(items = student){ student ->
            Column (
                modifier = Modifier
                    .clickable{
                        navController.navigate("Details/${student.id}")
                    }
                    .padding(vertical = 12.dp)
            ){
                Text(
                    text = student.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = student.description,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


