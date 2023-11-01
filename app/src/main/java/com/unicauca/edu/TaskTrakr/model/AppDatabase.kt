package com.unicauca.edu.TaskTrakr.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.unicauca.edu.TaskTrakr.controller.ClsCategory
import com.unicauca.edu.TaskTrakr.controller.ClsTask
import com.unicauca.edu.TaskTrakr.controller.Converters
import com.unicauca.edu.TaskTrakr.controller.User

@Database(entities = [ClsTask::class,ClsCategory::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao():TaskDao
    abstract  fun categoryDao():CategoryDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            val database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "database_task"
            ).allowMainThreadQueries().build()

            // Llama a un método para inicializar las categorías por defecto si la tabla está vacía.

            if (database.categoryDao().getAllCategorias().isEmpty()) {
                database.categoryDao().insertDefaultCategories()
            }

            return database
        }
    }
}