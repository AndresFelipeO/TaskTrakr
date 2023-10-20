package com.unicauca.edu.TaskTrakr.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.unicauca.edu.TaskTrakr.R

data class colorItemObject(val text: String, val color: Color)
data class dayItemObject(val day: String, val num: String)

data class hourItemObject(val hour: String, val mins: String)

@Composable
fun NewTask(){
    Column(modifier = Modifier
        .padding(20.dp)
        .background(MaterialTheme.colorScheme.background)){
        Text(stringResource(id = R.string.new_task),style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        Text(stringResource(id = R.string.title),style = MaterialTheme.typography.headlineSmall)
        BasicTextField(
            value = "Nombre...",
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .height(56.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp)
        )

        Text(stringResource(id = R.string.category),style = MaterialTheme.typography.headlineSmall)
        colorList()
        Spacer(modifier = Modifier.height(10.dp))

        Text(stringResource(id = R.string.location),style = MaterialTheme.typography.headlineSmall)
        BasicTextField(
            value = "Edificio...",
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .height(56.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(stringResource(id = R.string.reminder),style = MaterialTheme.typography.headlineSmall)
        BasicTextField(
            value = "Documentos, Tareas, etc...",
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .height(90.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(stringResource(id = R.string.date),style = MaterialTheme.typography.headlineSmall)
        dayList()
        Spacer(modifier = Modifier.height(10.dp))

        Text(stringResource(id = R.string.hour),style = MaterialTheme.typography.headlineSmall)
        hourList()

        Button(onClick = {}) {
            Text(text = stringResource(id = R.string.save),style = MaterialTheme.typography.bodyMedium)
        }
    }
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
                .size(40.dp)
                .background(item.color, shape = CircleShape)
                .clickable {}
        ){}
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun colorList(){
    val coloListObject = listOf(
        colorItemObject("Trabajo",Color.Red),
        colorItemObject("Universidad",Color.Magenta),
        colorItemObject("Tareas",Color.Cyan),
        colorItemObject(stringResource(id = R.string.add),Color.LightGray)
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

@Composable
fun dayItem(item:dayItemObject){
    Box(
        modifier = Modifier
            .size(70.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {}
    ){
        Column(modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .background(color = Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = item.day,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.num,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun dayList(){
    val dayListObject = listOf(
        dayItemObject("DOM","10"),
        dayItemObject("TUE","18"),
        dayItemObject("MON","20"),
        dayItemObject("SAT","25"),
        dayItemObject("","+")
    )

    LazyRow (modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .height(80.dp)
        .padding(10.dp)){
        items(dayListObject) { item ->
            dayItem(item)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun hourItem(item:hourItemObject){
    Box(
        modifier = Modifier
            .size(70.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {}
    ){
        Row(modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .background(color = Color.LightGray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(
                text = item.hour,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.mins,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun hourList(){
    val hourListObject = listOf(
        hourItemObject("21:","00"),
        hourItemObject("9:","15"),
        hourItemObject("13:","30"),
        hourItemObject("+","")
    )

    LazyRow (modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .height(80.dp)
        .padding(10.dp)){
        items(hourListObject) { item ->
            hourItem(item)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewTaskPreview(){
    NewTask()
}

@Composable
@Preview(showBackground = true)
fun colorItemPreview() {
    colorList()
}