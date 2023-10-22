package com.unicauca.edu.TaskTrakr.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.unicauca.edu.TaskTrakr.R


class MyAppNavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: MyAppTopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}
data class MyAppTopLevelDestination(
    val route:String,
    val selectedIcon:ImageVector,
    val iconTextId:Int
)

val TOP_LEVEL_DESTINATION= listOf(
   MyAppTopLevelDestination( route=MyAppRoute.TASK,
       selectedIcon= Icons.Default.DateRange,
       iconTextId= R.string.tasks),
    MyAppTopLevelDestination( route=MyAppRoute.NEWTASK,
        selectedIcon= Icons.Default.Edit,
        iconTextId= R.string.new_task),
    MyAppTopLevelDestination( route=MyAppRoute.SETTINGS,
        selectedIcon= Icons.Default.Settings,
        iconTextId= R.string.settings)
)

object MyAppRoute{
    const val NEWTASK="NewTask"
    const val TASK="Task"
    const val CATEGORY="Category"
    const val SETTINGS="Settings"
}
