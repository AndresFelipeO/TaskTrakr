package com.unicauca.edu.TaskTrakr

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.compose.AppTheme
import com.unicauca.edu.TaskTrakr.controller.User
import com.unicauca.edu.TaskTrakr.model.AppDatabase
import com.unicauca.edu.TaskTrakr.view.Class.clsTask
import com.unicauca.edu.TaskTrakr.view.MyAppNavigationActions
import com.unicauca.edu.TaskTrakr.view.MyAppRoute
import com.unicauca.edu.TaskTrakr.view.MyAppTopLevelDestination
import com.unicauca.edu.TaskTrakr.view.TOP_LEVEL_DESTINATION
import com.unicauca.edu.TaskTrakr.view.screens.Category
import com.unicauca.edu.TaskTrakr.view.screens.NewTask
import com.unicauca.edu.TaskTrakr.view.screens.Settings
import com.unicauca.edu.TaskTrakr.view.screens.Task
import com.unicauca.edu.TaskTrakr.view.screens.ViewTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    val taskList = mutableStateOf(mutableListOf<clsTask>())

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
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
                    println("entro")

                        val db = Room.databaseBuilder(
                            applicationContext,
                            AppDatabase::class.java, "database_name"
                        ).allowMainThreadQueries().build()

                        // La base de datos se ha creado exitosamente

                    val userDao = db.userDao()
                    lifecycleScope.launch {
                        try {
                            val newUser = User(uid = 1, firstName = "a", lastName = "b")
                            // Insertar el nuevo usuario en la base de datos
                            userDao.insertAll(newUser)

                            // Ahora puedes usar 'newUser' en el hilo principal
                            // Por ejemplo, puedes mostrar un mensaje de éxito en tu interfaz de usuario aquí
                            Log.d("Database", "Usuario insertado con éxito")
                        } catch (e: Exception) {
                            // En caso de un error, registra un mensaje de error
                            Log.e("Database", "Error al insertar el usuario: ${e.message}")
                        }
                    }



                    // Utiliza un CoroutineScope para ejecutar la operación en un hilo de fondo
                    lifecycleScope.launch {
                        try {
                            val users: List<User>
                            // Cambia al contexto de fondo
                            withContext(Dispatchers.IO) {
                                users = userDao.getAll()
                            }
                            println(users)
                            // Ahora puedes usar 'users' en el hilo principal
                            // Por ejemplo, puedes mostrar los datos en tu interfaz de usuario aquí
                            // Ejemplo: textView.text = users.joinToString { it.userName }
                        } catch (e: Exception) {
                            Log.e("Database", "Error al acceder a la base de datos: ${e.message}")
                        }


                    }
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
                            BottomAppBar(
                                containerColor =   Color.Transparent,
                            ) {
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
