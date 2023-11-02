package com.unicauca.edu.TaskTrakr.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unicauca.edu.TaskTrakr.R

data class SettingItem(val text: String, val icon: Int, val function: () -> Unit)

@Composable
fun Settings(navController: NavController){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background).padding(all = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = stringResource(id = R.string.settings),style=MaterialTheme.typography.headlineLarge,
            fontSize =35.sp, fontWeight = FontWeight.Bold )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsScreen(navController)
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    val settingsList = listOf(
        SettingItem(stringResource(id = R.string.default_reminder), R.drawable.clock_icon){

        },
        SettingItem(stringResource(id = R.string.notifications), R.drawable.notification_icon) {

        },
        SettingItem(stringResource(id = R.string.completed_tasks), R.drawable.checklist_icon) {

        },
        SettingItem(stringResource(id = R.string.rate_us), R.drawable.star_icon) {

        },
        SettingItem(stringResource(id = R.string.privacy_policies), R.drawable.exclamation_icon) {
            navController.navigate("information")
        },
        SettingItem(stringResource(id = R.string.bug_report), R.drawable.bug_icon) {

        },
        SettingItem(stringResource(id = R.string.share), R.drawable.share_icon) {

        }
        // Add more settings items as needed
    )
    Card {
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)){
            items(settingsList) { item ->
                SettingItemRow(item)
            }
        }
    }

}


@Composable
fun SettingItemRow(item: SettingItem) {
    Divider(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp), thickness = 1.dp, color = Color.Gray)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clickable(onClick = item.function)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.large
            )
            .padding(1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.text,
            style = MaterialTheme.typography.bodyLarge,
        )
        Icon(
            painter = painterResource(item.icon),
            contentDescription = "Icono"
        )
    }
}
