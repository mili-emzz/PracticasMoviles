package com.emiliagomez.examenpractico.roomComponents.di

import android.content.Context
import androidx.room.Room
import com.emiliagomez.examenpractico.roomComponents.room.StudentDatabase
import com.emiliagomez.examenpractico.roomComponents.room.StudentDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton

    fun provideStudentsDatabase(@ApplicationContext context: Context): StudentDatabase {
        return Room.databaseBuilder(
            context,
            StudentDatabase::class.java,
            "students_database"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton

    fun provideStudentsDatabaseDao(database: StudentDatabase): StudentDatabaseDao{
        return database.studentsDao()
    }

}