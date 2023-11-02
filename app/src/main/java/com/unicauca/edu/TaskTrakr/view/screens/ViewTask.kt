package com.unicauca.edu.TaskTrakr.view.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.R
import com.unicauca.edu.TaskTrakr.controller.ClsTask
import com.unicauca.edu.TaskTrakr.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTask(navController: NavController,taskId: Int){

    val context = LocalContext.current
    val taskDao = AppDatabase.getInstance(context).taskDao()
    val taskdb by produceState<ClsTask?>(initialValue = null) {
        try {
            val result: ClsTask? = withContext(Dispatchers.IO) {
                taskDao.getTaskById(taskId)
            }
            value = result
        } catch (e: Exception) {
            Log.e("Database", "Error al acceder a la base de datos: ${e.message}")
        }
    }
    val catedb by produceState<String?>(initialValue = null) {
        try {
            val result: String? = withContext(Dispatchers.IO) {
                taskDao.getTaskWithCategoryName(taskId)
            }
            value = result
        } catch (e: Exception) {
            Log.e("Database", "Error al acceder a la base de datos: ${e.message}")
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        bottomBar = {
            // No agregues ningún elemento en el BottomAppBar
            BottomAppBar(
                modifier = Modifier.height(0.dp),

            ) {}
        },

        topBar = {
            TopAppBar(

                title = {
                   Row (
                       Modifier
                           .padding(top = 5.dp)
                           .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

    
                   ){
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
                           taskdb?.let { Text(it.title, fontSize =35.sp, fontWeight = FontWeight.Bold ) }
                       }
                       IconButton(
                           onClick = {
                               // Navega hacia atrás en la navegación de la aplicación
                               taskdb?.let { taskDao.deleteTask(it) }
                               navController.popBackStack()
                           }
                       ) {
                           Icon(
                               imageVector = Icons.Default.Clear,
                               contentDescription = "delete",Modifier.size(35.dp)
                           )
                       }
                   }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 55.dp, start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row {
                    taskdb?.let { it1 ->
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = it1.location,
                            color = Color.Gray,
                            fontSize = 15.sp


                        )
                    }
                    catedb?.let { it1 ->
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "#$it1",
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }
                }
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
                ){
                    taskdb?.let { it1 ->
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = it1.reminder,
                            fontSize = 18.sp,
                            overflow = TextOverflow.Clip,
                        )
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar",
                        tint = Color.Gray
                    )
                    taskdb?.let { it1 ->
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = it1.date,
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }
                    taskdb?.let { it1 -> Text(text = it1.hour) }
                    Icon(
                        painter = painterResource(R.drawable.clock_icon),
                        contentDescription = "Clock",
                    )
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        },
    )

}