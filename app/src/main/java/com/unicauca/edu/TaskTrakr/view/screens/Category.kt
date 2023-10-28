package com.unicauca.edu.TaskTrakr.view.screens

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.unicauca.edu.TaskTrakr.R

data class taskItemObject(val title:String, val date:String, val hour:String)
data class categoryItemObject(val title:String, val tasks:List<taskItemObject>)

@Composable
fun Category() {
    val taskListObject = listOf(
        taskItemObject("Parcial Movil", "28/08/23", "2:00pm"),
        taskItemObject("Parcial Redes", "28/08/23", "12:00pm"),
        taskItemObject("Entrega Software", "28/08/23", "2:00pm"),
        taskItemObject("Parcial Apliweb", "28/08/23", "12:00pm")
    )

    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)

    ) {
        Text(stringResource(id = R.string.category), style = MaterialTheme.typography.headlineLarge)
        TaskList(taskListObject)
    }
}

@Composable
fun TaskList(tasks: List<taskItemObject>) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Magenta, shape = CircleShape)
                    .clickable { /* Acción de clic aquí */ }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Universidad", style = MaterialTheme.typography.headlineSmall)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
        ) {
            items(tasks) { item ->
                TaskItem(item)
            }
        }
    }
}

@Composable
fun TaskGrid(tasks: List<taskItemObject>){
    LazyColumn(
        modifier = Modifier
            .padding(1.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
    ) {
        items(tasks) { item ->
            TaskItem(item)
        }
    }
}

@Composable
fun TaskItem(item:taskItemObject){
    Row(
        modifier = Modifier
            .padding(5.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        Text(text = item.title, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        Spacer(modifier = Modifier.width(35.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Icono",
                tint = Color.Gray,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(text = item.date, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.hour, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        Spacer(modifier = Modifier.width(16.dp))
        ClickableText(
            text = AnnotatedString(">"),
            onClick = {},
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun TaskItemPreview(){
    TaskItem(taskItemObject("Parcial Movil","28/08/23","2:00pm"))
}

@Composable
@Preview(showBackground = true)
fun TaskListPreview(){
    val taskListObject=listOf(
        taskItemObject("Parcial Movil","28/08/23","2:00pm"),
        taskItemObject("Parcial Redes","28/08/23","12:00pm"),
        taskItemObject("Entrega Software","28/08/23","2:00pm"),
        taskItemObject("Parcial Apliweb","28/08/23","12:00pm")
    )
    TaskList(taskListObject)
}

@Composable
@Preview(showBackground = true)
fun CategoryPreview(){
    Category()
}