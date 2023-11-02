package com.unicauca.edu.TaskTrakr.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.unicauca.edu.TaskTrakr.controller.ClsTask
import com.unicauca.edu.TaskTrakr.view.screens.Category
import com.unicauca.edu.TaskTrakr.view.screens.NewTask
import com.unicauca.edu.TaskTrakr.view.screens.Settings
import com.unicauca.edu.TaskTrakr.view.screens.Task
import com.unicauca.edu.TaskTrakr.view.screens.ViewTask
import com.unicauca.edu.TaskTrakr.view.screens.View


class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()
                    val navigateAction= remember(navController){
                        MyAppNavigationActions(navController)
                    }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val selectDestination=navBackStackEntry?.destination?.route?:MyAppRoute.TASK
                    val navigateTopLevelDestination = navigateAction::navigateTo

                    Scaffold(
                        floatingActionButton = {
                            if (selectDestination == MyAppRoute.TASK){
                                FloatingActionButton(onClick = {  navController.navigate("NewTask") },
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    shape = CircleShape,
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add")
                                }
                            }

                        },
                        bottomBar = {
                            if (selectDestination == MyAppRoute.TASK
                                || selectDestination == MyAppRoute.CATEGORY
                                || selectDestination == MyAppRoute.SETTINGS) {
                            BottomAppBar{
                                TOP_LEVEL_DESTINATION.forEach { destinations ->
                                    NavigationBarItem(
                                        colors = androidx.compose.material3.NavigationBarItemDefaults
                                            .colors(
                                                selectedIconColor =  MaterialTheme.colorScheme.primary,

                                            ),
                                        selected =selectDestination==destinations.route,
                                        onClick = { navigateTopLevelDestination(destinations) },
                                        label = {
                                            Text(text = stringResource(id = destinations.iconTextId),
                                                fontWeight = if (selectDestination == destinations.route) {
                                                    FontWeight.Bold
                                                } else {
                                                   FontWeight.Normal
                                                }
                                            )
                                        },
                                        icon = {
                                            Icon(

                                                imageVector = destinations.selectedIcon,
                                                tint = if (selectDestination == destinations.route) {
                                                    MaterialTheme.colorScheme.primary
                                                } else {
                                                    MaterialTheme.colorScheme.secondary
                                                },
                                                contentDescription = stringResource(id = destinations.iconTextId),

                                            )
                                        }
                                    )

                                }
                            }}
                        },
                    ){
                        MyAppContent(
                            navController = navController,
                            selectDestination = selectDestination,
                            navigateTopLevelDestination = navigateAction::navigateTo
                        )
                    }




                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyAppContent(
    modifier: Modifier=Modifier,
    navController: NavHostController,
    selectDestination: String,
    navigateTopLevelDestination: (MyAppTopLevelDestination) -> Unit
){
    val taskState = remember { mutableStateOf<ClsTask?>(null) }

    Row(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                modifier = Modifier.weight(1.0F),
                navController = navController,
                startDestination = MyAppRoute.TASK,
            ){
                composable(MyAppRoute.TASK){
                    Task(navController) {}
                }
                composable(MyAppRoute.NEWTASK){
                    NewTask(navController)
                }
                composable(MyAppRoute.CATEGORY){
                    Category(navController)
                }
                composable(MyAppRoute.SETTINGS){
                    Settings(navController)
                }
                composable(MyAppRoute.INFO){
                    View()
                }
                composable(route = "${MyAppRoute.VIEWTASK}/{taskId}") { backStackEntry ->
                    val taskId = backStackEntry.arguments?.getString("taskId")?.toInt()
                    if (taskId != null) {
                        ViewTask(navController, taskId)
                    } else {
                        // Manejar el caso en el que no se proporciona un ID válido
                    }
                }

            }
        }
    }

}