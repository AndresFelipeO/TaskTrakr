package com.unicauca.edu.TaskTrakr.view.screens

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unicauca.edu.TaskTrakr.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.controller.ClsTask
import com.unicauca.edu.TaskTrakr.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Task(navController: NavController, tasks: (Any?) -> Unit){
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var text by remember { mutableStateOf(selectedDate.timeInMillis.toString()) }
    var tasksState by remember { mutableStateOf(tasks) }

    val context = LocalContext.current
    val taskDao = AppDatabase.getInstance(context).taskDao()


    val taskdb by produceState<List<ClsTask>?>(initialValue = null) {
        try {
            val result: List<ClsTask> = withContext(Dispatchers.IO) {
                taskDao.getAllTasks()
            }
            value = result
        } catch (e: Exception) {
            Log.e("Database", "Error al acceder a la base de datos: ${e.message}")
        }
    }


    var todayTask= mutableListOf<ClsTask>()

    taskdb?.forEach { task ->
        val dateFormat = DateTimeFormatter.ofPattern(
            "EEEE, MMMM d, yyyy",
            Locale.ENGLISH
        ) // Define el formato de fecha

        try {
            val dateAndTimeParts = task.date.split(", hour=", " ")
            val parsedDate = LocalDate.parse(
                "${dateAndTimeParts[0]} ${dateAndTimeParts[1]} ${dateAndTimeParts[2]} ${dateAndTimeParts[3]}",
                dateFormat
            ) // Convierte la parte de la fecha en un objeto LocalDate
            val today = LocalDate.now()
            if (parsedDate == today) {
                todayTask.add(task)
            }
        } catch (e: DateTimeParseException) {
            // Maneja la excepción si el formato no coincide
            // Por ejemplo, muestra un mensaje de error
            println("Error al analizar la fecha: ${e.message}")
        }

    }

    Column (
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(stringResource(id = R.string.app_name),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 15.dp)

        )
        SimpleCalendar()
        Text(text = stringResource(id = R.string.today),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,

        )

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 70.dp)
        ) {
            todayTask.forEach { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .clickable {
                            navController.navigate("viewTask/${task.tid}")
                        },
                ) {
                    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 15.dp)) {
                        Text(
                            text = task.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = task.location)
                            Row {
                                Text(text = task.hour)
                                Icon(imageVector = Icons.Filled.Info, contentDescription = "a")
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SimpleCalendar() {

    var selectedYear by remember { mutableStateOf(LocalDate.now().year) }
    var selectedMonth by remember { mutableStateOf(LocalDate.now().month) }

    var chosenYear by remember { mutableStateOf(LocalDate.now().year) }
    var chosenMonth by remember { mutableStateOf(LocalDate.now().month.name) }

    var expanded by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }

    var sendDate by remember { mutableStateOf(LocalDate.now()) }

    val dateList = remember { mutableStateListOf<LocalDate>() }

    var yeara=LocalDate.now().year

    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        StatusBar()

        val firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value

        Row {
            Text("${LocalDate.now().month.name}  ${LocalDate.now().year}",

                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val weekdays = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")
            weekdays.forEach {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                )
            }
        }
        Box(
            Modifier
                .padding(16.dp, 0.dp)
                ) {
            LazyVerticalGrid(columns = GridCells.Fixed(7), modifier = Modifier
                .height(200.dp)
                .padding(0.dp, 8.dp)
            ) {
                val emptyCells = List(firstDayOfWeek - 1) { null }
                val daysInMonth = firstDayOfMonth.lengthOfMonth()
                items(emptyCells.size + daysInMonth + 2) { dayOfMonth ->
                    val day = dayOfMonth - emptyCells.size + 1

                    var backgroundColor = Color.Transparent

                    if (day >= 1 || day > daysInMonth) {
                        val currentDate = try {
                            LocalDate.of(selectedYear, selectedMonth, day)
                        } catch (e: DateTimeException) {
                            null
                        }
                        backgroundColor =
                           Color.Transparent
                    }
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(),

                        ) {
                        if (day==LocalDate.now().dayOfMonth){

                            ElevatedCard(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp)),

                                        colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    ),
                                ) {
                                Text(
                                    text = if (day <= 0 || day > daysInMonth) " " else day.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .background(backgroundColor)
                                        .width(32.dp)
                                        .height(32.dp)
                                        .padding(top = 4.dp)
                                        .clickable(
                                            enabled = !(day <= 0 || day > daysInMonth)
                                        ) {
                                            sendDate =
                                                LocalDate.of(selectedYear, selectedMonth, day)
                                            onDateClick(sendDate, dateList)
                                        },
                                )
                            }
                        }else{
                            Text(
                                text = if (day <= 0 || day > daysInMonth) " " else day.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(backgroundColor)
                                    .width(32.dp)
                                    .height(32.dp)
                                    .background(backgroundColor, CircleShape)
                                    .padding(top = 4.dp)
                                    .clickable(
                                        enabled = !(day <= 0 || day > daysInMonth)
                                    ) {
                                        sendDate = LocalDate.of(selectedYear, selectedMonth, day)
                                        onDateClick(sendDate, dateList)
                                    },
                            )
                        }

                    }


                }
            }
        }

    }
}
@Composable
fun StatusBar() {
    val context = LocalContext.current
    val window = (context as? Activity)?.window


    SideEffect {
        val controller = window?.decorView?.let { ViewCompat.getWindowInsetsController(it) }
        controller?.isAppearanceLightStatusBars = true
    }
}

fun onDateClick(date: LocalDate, dateList: MutableList<LocalDate>) {

    if (dateList.contains(date)) {
        dateList.remove(date)
    } else {
        dateList.add(date)
    }
}
