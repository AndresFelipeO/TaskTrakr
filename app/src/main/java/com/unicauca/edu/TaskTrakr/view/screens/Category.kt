package com.unicauca.edu.TaskTrakr.view.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.R
import com.unicauca.edu.TaskTrakr.controller.ClsCategory
import com.unicauca.edu.TaskTrakr.controller.ClsTask
import com.unicauca.edu.TaskTrakr.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(stringResource(id = R.string.Categories), fontSize =35.sp, fontWeight = FontWeight.Bold )
            })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 70.dp, start = 15.dp, end = 15.dp, bottom = 70.dp)

            ) {
                val context = LocalContext.current
                val categoryDao = AppDatabase.getInstance(context).categoryDao()

                val categories by produceState<List<ClsCategory>?>(initialValue = null) {
                    try {
                        val result: List<ClsCategory> = withContext(Dispatchers.IO) {
                            categoryDao.getAllCategorias()
                        }
                        value = result
                    } catch (e: Exception) {
                        Log.e("Database", "Error al acceder a la base de datos: ${e.message}")
                    }
                }

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    categories?.forEach{ cate->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                        ) {
                            Column {
                                Text(modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
                                    text = cate.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Divider(color = Color.Gray, thickness = 1.dp)

                                val context = LocalContext.current
                                val taskDao = AppDatabase.getInstance(context).taskDao()
                                val taskdb by produceState<List<ClsTask>?>(initialValue = null) {
                                    try {
                                        val result: List<ClsTask> = withContext(Dispatchers.IO) {
                                            taskDao.getTasksByCategoryId(cate.categoryId)
                                        }
                                        value = result
                                    } catch (e: Exception) {
                                        Log.e("Database", "Error al acceder a la base de datos: ${e.message}")
                                    }
                                }

                                taskdb?.forEach{ task ->
                                    Card(
                                        modifier = Modifier.height(55.dp),

                                        onClick = {
                                            navController.navigate("viewTask/${task.tid}")
                                        }) {
                                        Row (
                                            Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight()
                                                .padding(horizontal = 20.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ){
                                            Text(text = task.title)
                                            Row (
                                                verticalAlignment = Alignment.CenterVertically
                                            ){
                                                Icon(imageVector = Icons.Default.DateRange, contentDescription ="date" )
                                                val parts = task.date.split(", ")

                                                Column (
                                                    modifier = Modifier.padding(start = 10.dp),
                                                    horizontalAlignment = Alignment.Start
                                                ){
                                                    Text(text = "${parts[1]} ${parts[2]}")
                                                    Text(text = task.hour)
                                                }

                                            }


                                        }
                                    }
                                    Divider(color = Color.Gray, thickness = 1.dp)
                                }





                            }
                        }

                    }
                }


            }

        }

    )
}
