package com.emiliagomez.cuartitosa_app.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.emiliagomez.cuartitosa_app.data.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class StudentViewModel: ViewModel() {

    private val studentsList = listOf<Student>(
        Student(1,"Emilia", "Jefa de grupo","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.prestigeonline.com%2Fsg%2Flifestyle%2Fculture-plus-entertainment%2Fthings-to-know-about-chiikawa-characters%2F&psig=AOvVaw22U4PDALjj6h8pc_ZdAEnR&ust=1760650020386000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCKDUvsqSp5ADFQAAAAAdAAAAABAE" ),
        Student(2,"Nadia", "Mi amiguita :33","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.prestigeonline.com%2Fsg%2Flifestyle%2Fculture-plus-entertainment%2Fthings-to-know-about-chiikawa-characters%2F&psig=AOvVaw1CC_EsFw5AWBaZxzuymMfH&ust=1760650175243000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCNiO_JOTp5ADFQAAAAAdAAAAABAE" ),
        Student(3, "Toño", "Mi amiguito", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwwe.fandom.com%2Fes%2Fwiki%2FCategor%25C3%25ADa%3ALuchadores&psig=AOvVaw0KzeMWhujvPfpfMI0HplBF&ust=1760650071670000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCKCt_eOSp5ADFQAAAAAdAAAAABAE"),
        Student(4, "Tony", "Mi papu","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.reddit.com%2Fr%2Fchiikawa_%2Fcomments%2F1l7d30b%2Fis_the_name_chiikawa_referring_to_all_the%2F%3Ftl%3Des-419&psig=AOvVaw1CC_EsFw5AWBaZxzuymMfH&ust=1760650175243000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCNiO_JOTp5ADFQAAAAAdAAAAABAZ"),
        Student(5, "Isaac", "Mi papá es vicentefox x10", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.zalora.com.ph%2Fblog%2Flifestyle%2Feverything-you-need-to-know-about-chiikawa%2F&psig=AOvVaw1CC_EsFw5AWBaZxzuymMfH&ust=1760650175243000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCNiO_JOTp5ADFQAAAAAdAAAAABAR")

    )
    private val _students = MutableStateFlow<List<Student>>(studentsList)
    val students: StateFlow<List<Student>> = _students.asStateFlow()
    private var idCounter = 6

    fun getAllStudents(): StateFlow<List<Student>> {
        return students
    }

    fun createStudent(name: String, description: String, image: String): Int {
        val newStudent = Student(
            id = idCounter++,
            name = name,
            description = description,
            image = image
        )
        val newId = idCounter
        idCounter++

        _students.update { currentList ->
            currentList + newStudent
        }

        return newId
    }
}