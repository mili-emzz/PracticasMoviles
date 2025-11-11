package com.emiliagomez.examenpractico.roomComponents.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emiliagomez.examenpractico.roomComponents.model.Students

@Database(entities = [Students::class], version = 2, exportSchema = false)
abstract class StudentDatabase: RoomDatabase() { //superclase que no peude ser instanciada pero sus metpdps si
    abstract fun studentsDao(): StudentDatabaseDao
}