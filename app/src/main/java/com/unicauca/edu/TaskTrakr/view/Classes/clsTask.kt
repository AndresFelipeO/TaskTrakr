package com.unicauca.edu.TaskTrakr.view.Classes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date

class clsTask (date: String,
               time: String,
               title: String,
               details: String,
               location: String,
               category: String): ViewModel(){
    private var date: MutableState<Date> = mutableStateOf(Date())
    private var time: String by mutableStateOf("")
    private var title: String by mutableStateOf("")
    private var details: String by mutableStateOf("")
    private var location: String by mutableStateOf("")
    private var category: String by mutableStateOf("")

    fun getDateAsString(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(date)
    }

    fun getTimeAsString(): String {
        return time ?: ""
    }

    fun setDateFromString(dateString: String, monthAbbreviation: String) {
        val monthMap = mapOf(
            "JAN" to 1, "FEB" to 2, "MAR" to 3, "APR" to 4, "MAY" to 5, "JUN" to 6,
            "JUL" to 7, "AUG" to 8, "SEP" to 9, "OCT" to 10, "NOV" to 11, "DEC" to 12
        )

        val dateParts = dateString.split("/")
        val day = dateParts[0].toInt()
        val monthAbbreviation = monthAbbreviation.toUpperCase()
        val month = monthMap[monthAbbreviation] ?: throw IllegalArgumentException("Invalid month abbreviation")
        val year = dateParts[2].toInt()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        date.value=dateFormat.parse("$day/$month/$year")
    }


    fun setTime(timeString: String) {
        // Add validation for time format if needed
        time = timeString
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getDetails(): String {
        return details
    }

    fun setDetails(details: String) {
        this.details = details
    }

    fun getLocation(): String {
        return location
    }

    fun setLocation(location: String) {
        this.location = location
    }

    fun getCategory(): String {
        return category
    }

    fun setCategory(category: String) {
        this.category = category
    }
}
