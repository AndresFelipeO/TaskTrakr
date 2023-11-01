package com.unicauca.edu.TaskTrakr.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.unicauca.edu.TaskTrakr.controller.ClsTask

@Dao
interface TaskDao {
    @Query("SELECT * FROM ClsTask")
    fun getAllTasks(): List<ClsTask>

    @Insert
    fun insertTask(task: ClsTask)

    @Update
    fun updateTask(task: ClsTask)

    @Delete
    fun deleteTask(task: ClsTask)

    // Define otros métodos para operaciones CRUD relacionadas con ClsTask
}
