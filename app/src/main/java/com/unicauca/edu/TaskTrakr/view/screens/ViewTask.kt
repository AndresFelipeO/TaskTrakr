package com.unicauca.edu.TaskTrakr.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTask(navController: NavController){
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
                           Text("Parcial Móvil", fontSize =35.sp, fontWeight = FontWeight.Bold )
                       }
                       IconButton(
                           onClick = {
                               // Navega hacia atrás en la navegación de la aplicación
                               navController.popBackStack()
                           }
                       ) {
                           Icon(
                               imageVector = Icons.Default.Create,
                               contentDescription = "Atrás",Modifier.size(35.dp)
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
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Salon 334",
                        color = Color.Gray,
                        fontSize = 15.sp


                    )
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "#Universidad",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Parcial de diseño en android\n" +
                                "- Calidad de la app\n" +
                                "- monetizar\n" +
                                "- principios de diseño",
                        fontSize = 18.sp
                    )
                    Text(text = "4:00 pm ")
                    Icon(
                        painter = painterResource(R.drawable.clock_icon),
                        contentDescription = "Clock",
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar",
                        tint = Color.Gray
                    )
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Hoy",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        },
    )

}