package com.unicauca.edu.TaskTrakr.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class  Author(val name:String,val email:String)

@Preview
@Composable
fun View(){
    Body(author1 = Author("Andres Felipe Ocampo",
         "anocampo@unicauca.edu.co"),author2 = Author("Juan Camilo Carreño",
         " jccarreno@unicauca.edu.co"))
}

@Composable
fun Body(author1:Author, author2:Author){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            Text(text = "DEVELOPERS", fontSize = 30.sp, fontWeight = FontWeight.Bold,color = MaterialTheme.colorScheme.primary)
            Text(text = author1.name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = author2.name, fontSize = 25.sp, fontWeight = FontWeight.Bold)


        }
        Column(horizontalAlignment = Alignment.Start) {
            Info(icon = Icons.Rounded.Favorite, label ="https://github.com/AndresFelipeO/TaskTrakr.git" )
            Info(icon = Icons.Rounded.Email, label =author1.email )
            Info(icon = Icons.Rounded.Email, label =author2.email )
        }

    }

}

@Composable
fun Info (icon: ImageVector, label:String) {
    Row {
        Icon(icon,contentDescription="icon" ,Modifier.size(18.dp), tint = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text =label,)
    }
}