package com.emiliagomez.examenpractico

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emiliagomez.examenpractico.navigation.NavManager
import com.emiliagomez.examenpractico.store.StoreDarkMode
import com.emiliagomez.examenpractico.ui.theme.ExamenPracticoTheme
import com.emiliagomez.examenpractico.viewmodels.FormsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStore = StoreDarkMode(this)
        setContent {
            val viewModel: FormsViewModel = hiltViewModel()

            var isDarkTheme by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope() //funciones suspend

            // cuando empieza la app lee el tema guardado
            LaunchedEffect(Unit) {
                dataStore.isDarkTheme.collect { savedTheme ->
                    isDarkTheme = savedTheme
                }
            }

            ExamenPracticoTheme(
                darkTheme = isDarkTheme
            ) {
                NavManager(
                    isDarkTheme, { newTheme ->
                        isDarkTheme = newTheme

                        scope.launch { //escuchar el suspend de saveDarkMode
                            dataStore.saveDarkTheme(newTheme)
                        }
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}
