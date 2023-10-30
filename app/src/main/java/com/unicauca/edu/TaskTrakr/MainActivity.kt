package com.unicauca.edu.TaskTrakr

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.unicauca.edu.TaskTrakr.view.Classes.clsTask
import com.unicauca.edu.TaskTrakr.view.MyAppNavigationActions
import com.unicauca.edu.TaskTrakr.view.MyAppRoute
import com.unicauca.edu.TaskTrakr.view.MyAppTopLevelDestination
import com.unicauca.edu.TaskTrakr.view.TOP_LEVEL_DESTINATION
import com.unicauca.edu.TaskTrakr.view.screens.Category
import com.unicauca.edu.TaskTrakr.view.screens.NewTask
import com.unicauca.edu.TaskTrakr.view.screens.Settings
import com.unicauca.edu.TaskTrakr.view.screens.Task
import com.unicauca.edu.TaskTrakr.view.screens.ViewTask

class MainActivity : ComponentActivity() {

    val taskList = mutableStateOf(mutableListOf<clsTask>())

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        taskList.value.add(clsTask("21/08/2023","14:30","Parcial estadistica","Llevar pc","FIET 203","Universidad"))

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
                            FloatingActionButton(onClick = {  navController.navigate("NewTask") },
                                containerColor = Color.White,
                                shape = CircleShape,
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        },
                        bottomBar = {
                            BottomAppBar(
                                containerColor = Color.White,
                            ) {
                                TOP_LEVEL_DESTINATION.forEach { destinations ->
                                    NavigationBarItem(
                                        colors = androidx.compose.material3.NavigationBarItemDefaults
                                            .colors(
                                                selectedIconColor =  MaterialTheme.colorScheme.primary,
                                                indicatorColor = Color.White
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
                                                    Color.Gray
                                                },
                                                contentDescription = stringResource(id = destinations.iconTextId),

                                            )
                                        }
                                    )

                                }
                            }
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
    Row(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                modifier = Modifier.weight(1.0F),
                navController = navController,
                startDestination = MyAppRoute.TASK,
            ){
                composable(MyAppRoute.TASK){
                    Task(navController)
                }
                composable(MyAppRoute.NEWTASK){
                    NewTask()
                }
                composable(MyAppRoute.CATEGORY){
                    println("Entro")
                    Category()
                }
                composable(MyAppRoute.SETTINGS){
                    Settings()
                }
                composable(MyAppRoute.VIEWTASK){
                    ViewTask(navController)
                }

            }
        }
    }

}
