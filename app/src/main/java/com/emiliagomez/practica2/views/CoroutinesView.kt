package com.emiliagomez.practica2.views

import android.R.attr.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emiliagomez.practica2.viewModels.CoroutineVideModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoroutinesView(
        viewModel : CoroutineVideModel = viewModel(),
        navController: NavController? = null){

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Practica")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                navigationIcon = {
                    if (navController != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        }
    ) {
        padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            // Corutina1

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ){
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "1. Llamada simple",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.simpleCall() },
                        enabled = !viewModel.isLoading1.value
                    ){
                        Text("EJecutar")
                    }

                    // condiciones

                    if(viewModel.isLoading1.value){
                        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                    }

                    if(viewModel.result1.value.isNotEmpty()){
                        Text(
                            text = viewModel.result1.value,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Corutina2

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ){
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "2. Secuencial ig ",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.secuencialCall() },
                        enabled = !viewModel.isLoading2.value
                    ){
                        Text("EJecutar")
                    }

                    // condiciones

                    if(viewModel.isLoading2.value){
                        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                    }

                    if(viewModel.result2.value.isNotEmpty()){
                        Text(
                            text = viewModel.result2.value,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Corutina3

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ){
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "3. Contador",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.paralelCall() },
                        enabled = !viewModel.isLoading3.value
                    ){
                        Text("EJecutar")
                    }

                    // condiciones

                    if(viewModel.isLoading3.value){
                        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                    }

                    if(viewModel.result3.value.isNotEmpty()){
                        Text(
                            text = viewModel.result3.value,
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }
    }
}