package com.unicauca.edu.TaskTrakr.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unicauca.edu.TaskTrakr.controller.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}