package com.unicauca.edu.TaskTrakr

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.unicauca.edu.TaskTrakr.view.MyAppNavigationActions
import com.unicauca.edu.TaskTrakr.view.MyAppRoute
import com.unicauca.edu.TaskTrakr.view.MyAppTopLevelDestination
import com.unicauca.edu.TaskTrakr.view.TOP_LEVEL_DESTINATION
import com.unicauca.edu.TaskTrakr.view.screens.Category
import com.unicauca.edu.TaskTrakr.view.screens.NewTask
import com.unicauca.edu.TaskTrakr.view.screens.Settings
import com.unicauca.edu.TaskTrakr.view.screens.Task

class MainActivity : ComponentActivity() {
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

@Composable
fun MainScreen(
    selectDestination:String,
    navigateTopLevelDestination:(MyAppTopLevelDestination)->Unit
) {
   NavigationBar(
       modifier = Modifier.fillMaxWidth()
   ) {
    TOP_LEVEL_DESTINATION.forEach { destinations ->
        NavigationBarItem(selected =selectDestination==destinations.route,
            onClick = { navigateTopLevelDestination(destinations) },
            icon = {
                Icon(imageVector = destinations.selectedIcon, 
                    contentDescription = stringResource(id = destinations.iconTextId))
            }
        )

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
                    Task()
                }
                composable(MyAppRoute.CATEGORY){
                    Category()
                }
                composable(MyAppRoute.SETTINGS){
                    Settings()
                }
                composable(MyAppRoute.NEWTASK){
                    NewTask()
                }
            }
            MainScreen(selectDestination=selectDestination,
                navigateTopLevelDestination=navigateTopLevelDestination)
        }
    }

}
