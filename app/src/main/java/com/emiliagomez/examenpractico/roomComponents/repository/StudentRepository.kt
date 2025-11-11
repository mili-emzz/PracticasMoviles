package com.emiliagomez.examenpractico.roomComponents.repository

import com.emiliagomez.examenpractico.roomComponents.model.Students
import com.emiliagomez.examenpractico.roomComponents.room.StudentDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//al yp inyectar una dependencia aca esta clase tmb se hace inyectable y es una cadena
class StudentRepository @Inject constructor(private val studentDatabaseDao: StudentDatabaseDao) {
    suspend fun  addStudent(students: Students) = studentDatabaseDao.insert(students)
    suspend fun  updateStudent(students: Students) = studentDatabaseDao.update(students)
    suspend fun  deleteStudent(students: Students) = studentDatabaseDao.delete(students)
    fun getAllStudents(): Flow<List<Students>> = studentDatabaseDao.getStudent().flowOn(Dispatchers.IO).conflate()
    fun getStudentById(id: Int): Flow<Students> = studentDatabaseDao.getStudentById(id).flowOn(Dispatchers.IO).conflate()

}