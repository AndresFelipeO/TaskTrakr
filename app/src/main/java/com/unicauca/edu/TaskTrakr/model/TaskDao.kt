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

    @Query("SELECT * FROM ClsTask WHERE tid = :taskId")
    fun getTaskById(taskId: Int): ClsTask?

    @Query("SELECT categories.name AS categoryName FROM ClsTask AS tasks INNER JOIN ClsCategory AS categories ON tasks.category = categories.categoryId WHERE tasks.tid = :taskId")
    fun getTaskWithCategoryName(taskId: Int): String

    @Query("SELECT * FROM ClsTask WHERE category = :categoryId")
    fun getTasksByCategoryId(categoryId: Int): List<ClsTask>

}
