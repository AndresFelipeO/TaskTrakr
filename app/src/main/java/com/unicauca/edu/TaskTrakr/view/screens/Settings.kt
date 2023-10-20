package com.unicauca.edu.TaskTrakr.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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

data class SettingItem(val text: String, val icon: Int)

@Composable
fun Settings(){
    Column (modifier = Modifier.fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(R.drawable.settings_icon),
                contentDescription = "SettingsIcon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(id = R.string.settings),style=MaterialTheme.typography.headlineLarge)
        }
        Spacer(modifier = Modifier.width(30.dp))
        SettingsScreen()
    }
}

@Composable
fun SettingsScreen() {
    val settingsList = listOf(
        SettingItem(stringResource(id = R.string.default_reminder), R.drawable.clock_icon),
        SettingItem(stringResource(id = R.string.notifications), R.drawable.notification_icon),
        SettingItem(stringResource(id = R.string.completed_tasks), R.drawable.checklist_icon),
        SettingItem(stringResource(id = R.string.rate_us), R.drawable.star_icon),
        SettingItem(stringResource(id = R.string.privacy_policies), R.drawable.exclamation_icon),
        SettingItem(stringResource(id = R.string.bug_report), R.drawable.bug_icon),
        SettingItem(stringResource(id = R.string.share), R.drawable.share_icon)
        // Add more settings items as needed
    )

    LazyColumn (modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)){
        items(settingsList) { item ->
            SettingItemRow(item)
        }
    }
}


@Composable
fun SettingItemRow(item: SettingItem) {
    Divider(modifier = Modifier.fillMaxWidth().padding(16.dp), thickness = 1.dp, color = Color.Gray)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {
                // Handle item click
            })
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.large
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.text,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            painter = painterResource(item.icon),
            contentDescription = "Icono"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSettingsScreen() {
    SettingsScreen()
}

@Composable
@Preview(showBackground = true)
fun PreviewSettings() {
    Settings()
}