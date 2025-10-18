package com.emiliagomez.cuartitosa_app.presentation.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.emiliagomez.cuartitosa_app.data.Student
import com.emiliagomez.cuartitosa_app.viewmodel.StudentViewModel
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsViews(navController: NavHostController, id: Long, viewModel: StudentViewModel = viewModel()) {

    // Observa la lista de estudiantes como un estado
    val studentList by viewModel.students.collectAsState()
    val student = studentList.find{
        it.id.toLong() == id
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(student?.name ?: "Details")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,contentDescription ="")
                    }
                }
            )
        }
    ) {
        DetailsContent(it, student)
    }
}

@Composable
fun DetailsContent(paddingValues: PaddingValues, student: Student?){
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize() // Ocupa toda la pantalla
            .padding(16.dp), // Añade padding general

        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (student != null){
            Text(
                text = student.name,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = student.description,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            Text("Estudiante no encontrado")
        }
    }
}