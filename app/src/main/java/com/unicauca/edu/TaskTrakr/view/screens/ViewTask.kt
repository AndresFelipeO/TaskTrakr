package com.unicauca.edu.TaskTrakr.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.R


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//navController: NavController
fun ViewTask(){
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                   Row (

                       Modifier.padding(top = 5.dp).fillMaxWidth(),
                       Arrangement.SpaceEvenly
                   ){
                       Row {

                           IconButton(

                               onClick = {
                                   // Navega hacia atrás en la navegación de la aplicación
                                   // navController.popBackStack()
                               }
                           ) {
                               Icon(
                                   imageVector = Icons.Default.KeyboardArrowLeft,
                                   contentDescription = "Atrás",Modifier.size(30.dp)
                               )
                           }
                           Text("Parcial Móvil", fontSize =30.sp, fontWeight = FontWeight.Bold )
                       }
                       IconButton(
                           onClick = {
                               // Navega hacia atrás en la navegación de la aplicación
                               // navController.popBackStack()
                           }
                       ) {
                           Icon(
                               imageVector = Icons.Default.Create,
                               contentDescription = "Atrás",Modifier.size(30.dp)
                           )
                       }
                   }
                }
            )
        },
    )
    {


        Text(text = "Hola")
    }
}