package com.unicauca.edu.TaskTrakr.view.Class

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date

class clsTask(
    initialDate: String,
    initialTime: String,
    initialTitle: String,
    initialDetails: String,
    initialLocation: String,
    initialCategory: String
) : ViewModel() {
    private var date: MutableState<Date> = mutableStateOf(Date())
    private var time: String by mutableStateOf(initialTime)
    private var title: String by mutableStateOf(initialTitle)
    private var details: String by mutableStateOf(initialDetails)
    private var location: String by mutableStateOf(initialLocation)
    private var category: String by mutableStateOf(initialCategory)

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


    fun setTimeValue(timeString: String) {
        time = timeString
    }

    fun getTitleValue(): String {
        return title
    }

    fun setTitleValue(title: String) {
        this.title = title
    }

    fun getDetailsValue(): String {
        return details
    }

    fun setDetailsValue(details: String) {
        this.details = details
    }

    fun getLocationValue(): String {
        return location
    }

    fun setLocationValue(location: String) {
        this.location = location
    }

    fun getCategoryValue(): String {
        return category
    }

    fun setCategoryValue(category: String) {
        this.category = category
    }

}
