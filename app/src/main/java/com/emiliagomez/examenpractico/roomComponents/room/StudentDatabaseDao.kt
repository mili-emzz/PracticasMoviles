package com.emiliagomez.examenpractico.roomComponents.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.emiliagomez.examenpractico.roomComponents.model.Students
import kotlinx.coroutines.flow.Flow

//bd y un crud aca bn chingon

@Dao //data access observer
interface StudentDatabaseDao {
    // crud
    @Query("SELECT * FROM students")
    fun getStudent(): Flow<List<Students>> //,muchos registros

    @Query("SELECT * FROM students WHERE id = :id")
    fun getStudentById(id: Int): Flow<Students>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // si hay un problema al guardar reemplazalo alav
    suspend fun insert(students: Students) //corutina

    @Update(onConflict = OnConflictStrategy.REPLACE) // si hay un problema al guardar reemplazalo alav
    suspend fun update(students: Students)

    @Delete
    suspend fun delete(students: Students)
}