package com.unicauca.edu.TaskTrakr.model

interface Connection<T> {
    fun create(item: T)

    fun delete(id:String)

    fun update(item: T)

    fun read(): List<T>
}