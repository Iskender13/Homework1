package com.example.homework12.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework12.Model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}