package com.unicauca.edu.TaskTrakr.view.screens

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.R
import com.unicauca.edu.TaskTrakr.view.Class.CalendarDataSource
import com.unicauca.edu.TaskTrakr.view.Class.CalendarUiModel
import com.unicauca.edu.TaskTrakr.view.Class.clsTask
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Calendar
import java.util.Locale

data class colorItemObject(val text: String, val color: Color)
data class dayItemObject(val month: String, val num: String)

data class hourItemObject(val hour: String, val mins: String)

var date: String=""
var time: String=""
var category: String=""

@Preview
@Composable
fun NewTask(navController: NavController){
    var title by remember { mutableStateOf("Nombre...") }
    var location by remember { mutableStateOf("Edificio...") }
    var details by remember { mutableStateOf("Documents...") }


    Column(modifier = Modifier
        .padding(20.dp)
        .background(MaterialTheme.colorScheme.background)
        .verticalScroll(state = rememberScrollState(), enabled = true)){
        Row {
            IconButton(

                onClick = {
                    // Navega hacia atrás en la navegación de la aplicación
                  navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Atrás",Modifier.size(30.dp)
                )
            }
            Text(stringResource(id = R.string.new_task), fontSize =35.sp, fontWeight = FontWeight.Bold )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            stringResource(id = R.string.title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        BasicTextField(
            value =title,  // Accede al valor dentro de la variable mutable
            onValueChange = { title= it },  // Actualiza el valor dentro de la variable mutable
            textStyle = TextStyle(color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
                .height(56.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.category),
            style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        colorList()
        Spacer(modifier = Modifier.height(10.dp))

        Text(stringResource(id = R.string.location),style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        BasicTextField(
            value =location,  // Accede al valor dentro de la variable mutable
            onValueChange = { location= it },  // Actualiza el valor dentro de la variable mutable
            textStyle = TextStyle(color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
                .height(56.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(stringResource(id = R.string.reminder),style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        BasicTextField(
            value =details,  // Accede al valor dentro de la variable mutable
            onValueChange = { details= it },  // Actualiza el valor dentro de la variable mutable
            textStyle = TextStyle(color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
                .height(56.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.date),style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        CalendarApp()
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.hour),style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        var hours by remember { mutableStateOf("1") }
        var minutes by remember { mutableStateOf("0") }

        var isAM by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // BasicTextField para las horas (de 1 a 12)
                BasicTextField(
                    value = hours,
                    onValueChange = {
                        if (it.isNumeric() && it.toInt() in 0..12) {
                            hours = it
                        }
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.width(64.dp)
                )

                // BasicTextField para los minutos (de 0 a 59)
                BasicTextField(
                    value = minutes,
                    onValueChange = {
                        if (it.isNumeric() && it.toInt() in 0..59) {
                            minutes = it
                        }
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.width(64.dp)
                )
                TimePeriodButton("AM", isAM) {
                    isAM = true
                }
                Spacer(modifier = Modifier.width(16.dp))
                TimePeriodButton("PM", !isAM) {
                    isAM = false
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = {
            clsTask(
                "21/OCT/2023",
                "21:56",
                title,
                details,
                location,
                category
            )//Guardar la tarea y volver a la pantalla de inicio
        }) {
            Text(
                text = stringResource(id = R.string.save),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(70.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePeriodButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(

        modifier = Modifier
            .fillMaxHeight(),
        onClick = onClick,

        colors = CardDefaults.cardColors(
            containerColor =
            if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

        ),
    ) {
        Text(text = "     $label    ", textAlign = TextAlign.Center, modifier = Modifier.padding(all = 10.dp))
    }
}

fun String.isNumeric(): Boolean {
    return this.matches("\\d+".toRegex())
}

@Composable
fun colorItem(item:colorItemObject){
    Column(modifier = Modifier
        .width(80.dp)
        .height(60.dp)
        .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            modifier = Modifier
                .size(25.dp)
                .background(item.color, shape = CircleShape)
                .clickable { category = item.text }
        ){}

        Text(
            text = item.text,
            style = MaterialTheme.typography.bodyMedium,

        )
    }
}


@Composable
fun colorList(){
    val coloListObject = listOf(
        colorItemObject("Trabajo",Color.Red),
        colorItemObject("Universidad",Color.Magenta),
        colorItemObject("Tareas",Color.Cyan),
        colorItemObject("Casa",Color.LightGray)
    )

    LazyRow (modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .height(80.dp)
        .padding(10.dp)){
        items(coloListObject) { item ->
            colorItem(item)
        }
    }
}

//------------------------------------------------------------

@SuppressLint("NewApi")
@Composable
fun CalendarApp(modifier: Modifier = Modifier) {
    val dataSource = CalendarDataSource()
    // we use `mutableStateOf` and `remember` inside composable function to schedules recomposition
    var calendarUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }

    Column(modifier = modifier.fillMaxSize()) {
        Header(
            data = calendarUiModel,
            onPrevClickListener = { startDate ->
                val finalStartDate = startDate.minusDays(1)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            },
            onNextClickListener = { endDate ->
                val finalStartDate = endDate.plusDays(2)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            }
        )
        Content(data = calendarUiModel, onDateClickListener = { date ->
            calendarUiModel = calendarUiModel.copy(
                selectedDate = date,
                visibleDates = calendarUiModel.visibleDates.map {
                    it.copy(
                        isSelected = it.date.isEqual(date.date)
                    )
                }
            )
        })
    }
}

@SuppressLint("NewApi")
@Composable
fun Header(data: CalendarUiModel,
           onPrevClickListener: (LocalDate) -> Unit,
           onNextClickListener: (LocalDate) -> Unit,) {
    Row {

        Text(
            // show "Today" if user selects today's date
            // else, show the full format of the date
            text = if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                )
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = {
            // invoke previous callback when its button clicked
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Back"
            )
        }
        IconButton(onClick = {
            // invoke next callback when this button is clicked
            onNextClickListener(data.endDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Next"
            )
        }
    }
}

@Composable
fun Content(
    data: CalendarUiModel,
    // callback should be registered from outside
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    LazyRow {
        items(items = data.visibleDates) { date ->
            ContentItem(
                date = date,
                onDateClickListener
            )
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun ContentItem(date: CalendarUiModel.Date,
                onClickListener: (CalendarUiModel.Date) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable { // making the element clickable, by adding 'clickable' modifier
                onClickListener(date)
            }
        ,
        colors = CardDefaults.cardColors(
            // background colors of the selected date
            // and the non-selected date are different
            containerColor = if (date.isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}