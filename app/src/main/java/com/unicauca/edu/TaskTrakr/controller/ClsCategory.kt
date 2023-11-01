package com.unicauca.edu.TaskTrakr.controller

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClsCategory (
    @PrimaryKey(autoGenerate = true) val categoryId: Int = 0, // Se generará automáticamente
    @ColumnInfo(name = "name") val name: String,
    val isSelected: Boolean = false
)