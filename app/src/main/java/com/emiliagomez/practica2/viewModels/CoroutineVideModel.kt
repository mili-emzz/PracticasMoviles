package com.emiliagomez.practica2.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoroutineVideModel: ViewModel() {
    val result1 = mutableStateOf("")
    val isLoading1 = mutableStateOf(false)
    val result2 = mutableStateOf("")
    val isLoading2 = mutableStateOf(false)
    val result3 = mutableStateOf("")
    val isLoading3 = mutableStateOf(false)



    fun simpleCall(){
        viewModelScope.launch {

            result1.value = "Pero si me va a pasar?"
            isLoading1.value = true
            result1.value = withContext(Dispatchers.IO){ // que tantos hilos de tareas puede hacer, io aguanta 64
                delay(3000)
                "Llamada simple lista"
            }
            isLoading1.value = false
        }
    }


    fun secuencialCall(){
        viewModelScope.launch{
            isLoading2.value = true
            result2.value = "Cargando..."

            val token = withContext(Dispatchers.IO){
                delay(3000)
                "123PRCTCAIA"
            }

            result2.value = "Busco el token $token..."

            val perfil = withContext(Dispatchers.IO){
                delay(1500)
                "Usuario: John Cena"
            }

            result2.value = "Usuaurio obtenido: $perfil"
            isLoading2.value = false
        }
    }

    fun paralelCall(){
        viewModelScope.launch {
            isLoading3.value = true
            result3.value = "Contador de 5 segundos XDD"

            for (i in 1..5){
                delay(1000)
                result3.value = "$i s"
            }

            result3.value = "Ya me aburrí"
            isLoading3.value = false
        }
    }
}