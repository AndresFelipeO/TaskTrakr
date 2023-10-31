package com.unicauca.edu.TaskTrakr.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unicauca.edu.TaskTrakr.R


@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category() {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(stringResource(id = R.string.Categories), fontSize =35.sp, fontWeight = FontWeight.Bold )
            })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 70.dp, start = 15.dp, end = 15.dp),
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
                            text = "Universidad", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Divider(color = Color.Gray, thickness = 1.dp)
                        Card(
                            modifier = Modifier.height(40.dp),

                            onClick = { /*TODO*/ }) {
                            Row (
                                Modifier
                                    .fillMaxWidth().fillMaxHeight()
                                    .padding(horizontal = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(text = "Parcial Móvil")
                                Row {
                                    Icon(imageVector = Icons.Default.DateRange, contentDescription ="date" )
                                    Text(text = "  29/07/23")
                                    Text(text = "  4:00Pm")

                                }

                            }
                        }


                    }
                }
            }

        }

    )
}
