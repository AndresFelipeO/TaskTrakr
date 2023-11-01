    package com.unicauca.edu.TaskTrakr.controller

    import androidx.room.ColumnInfo
    import androidx.room.Entity
    import androidx.room.ForeignKey
    import androidx.room.PrimaryKey

    @Entity(
        foreignKeys = [
            ForeignKey(
                entity = ClsCategory::class,
                parentColumns = ["categoryId"],
                childColumns = ["category"],
                onDelete = ForeignKey.CASCADE // Define la acción de eliminación (puede ser otra opción)
            )
        ]
    )
    data class ClsTask(
        @PrimaryKey(autoGenerate = true) val tid: Int = 0, // Se generará automáticamente
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "category") val categoryId: Int, // Hace referencia al categoryId de la categoría
        @ColumnInfo(name = "location") val location: String,
        @ColumnInfo(name = "reminder") val reminder: String,
        @ColumnInfo(name = "date") val date: String,
        @ColumnInfo(name = "hour") val hour: String
    )