package com.emiliagomez.examenpractico.roomComponents.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Students(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lastname")
    val lastname : String,
    @ColumnInfo( name = "password")
    val password : String
)
