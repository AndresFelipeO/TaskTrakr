package com.unicauca.edu.TaskTrakr.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.unicauca.edu.TaskTrakr.controller.ClsCategory

@Dao
interface CategoryDao {
    @Query("SELECT * FROM ClsCategory")
    fun getAllCategorias(): List<ClsCategory>

    @Insert
    fun insertCategoria(categoria: ClsCategory)

    @Update
    fun updateCategoria(categoria: ClsCategory)

    @Delete
    fun deleteCategoria(categoria: ClsCategory)

    @Transaction
    fun insertDefaultCategories() {
        val defaultCategories = listOf(
            ClsCategory(categoryId = 1, name = "Trabajo"),
            ClsCategory(categoryId = 2, name = "Universidad"),
            ClsCategory(categoryId = 3, name = "Personal")
        )

        defaultCategories.forEach { insertCategoria(it) }
    }
}