package com.emiliagomez.examenpractico.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emiliagomez.examenpractico.roomComponents.model.Students
import com.emiliagomez.examenpractico.roomComponents.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class FormsViewModel @Inject constructor(private val repository: StudentRepository) : ViewModel() {
    private val _studentList = MutableStateFlow<List<Students>>(emptyList())
    val studentList = _studentList.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllStudents().collect { item ->
                if(item.isEmpty()){
                    _studentList.value = emptyList()
                }else{
                    _studentList.value = item
                }
            }
        }
    }

    fun saveStudent(name: String, lastname: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _result.value = ""

            val newStudent = Students(
                name = name,
                lastname = lastname,
                password = password
            )

            repository.addStudent(newStudent)

            delay(2000)

            _loading.value = false
            _result.value = "Estudiante guardado"
        }
    }

    fun addStudent(students: Students) = viewModelScope.launch { repository.addStudent(students) }
    fun updateStudent(students: Students) = viewModelScope.launch { repository.updateStudent(students) }
    fun deleteStudent(students: Students) = viewModelScope.launch { repository.deleteStudent(students) }
}