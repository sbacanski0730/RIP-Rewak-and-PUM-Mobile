package com.example.lessonplanapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.lessonplanapp.Buildings.BuildingList
import com.example.lessonplanapp.Courses.CoursesList
import com.example.lessonplanapp.Departments.DepartmentsList
import com.example.lessonplanapp.Rooms.RoomNumbers.RoomNumbersList
import com.example.lessonplanapp.Specializations.SpecializationsList
import com.example.lessonplanapp.Workers.WorkerGroup.WorkersGroupList
import com.example.lessonplanapp.Workers.WorkerName.WorkersList
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.White

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            LessonPlanAppTheme() {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        HomeView(onClick = {
                            navController.navigate(it)
                        })
                    }

                    //Workers
                    composable("workersGroup"){
                        WorkersGroupList(onClick = {navController.navigate(it, navOptions{
                            popUpTo(it){inclusive=true}
                        })})
                    }
                    composable("departments/{workersGroup}", arguments = listOf(navArgument("workersGroup"){type = NavType.StringType})){
                        DepartmentsList(it.arguments?.get("workersGroup") as String,
                            onClick={
                                navController.navigate(it, navOptions{
                                    popUpTo(it){inclusive=true}
                                })
                            }
                        )
                    }
                    composable("workersGroups/{departmentName}/{workerName}",
                        arguments = listOf(
                            navArgument("departmentName"){type = NavType.StringType},
                            navArgument("workerName"){type = NavType.StringType}
                        )){
                        WorkersList(
                            it.arguments?.get("departmentName") as String,
                            it.arguments?.get("workerName") as String,
                            onClick={
                                navController.navigate(it, navOptions{
                                    popUpTo(it){inclusive=true}
                                })
                            }
                        )
                    }

                    //Buildings
                    composable("buildings"){
                        BuildingList(onClick = {
                            navController.navigate(it, navOptions{
                                popUpTo(it){inclusive=true}
                            })
                        })
                    }
                    composable("rooms/{buildingName}", arguments = listOf(navArgument("buildingName"){type = NavType.StringType}))
                    {
                        RoomsList(it.arguments?.get("buildingName") as String,
                            onClick={
                                navController.navigate(it, navOptions{
                                    popUpTo(it){inclusive=true}
                                })
                            }
                        )
                    }
                    composable("rooms/{buildingName}/{roomNumber}",
                        arguments = listOf(
                            navArgument("buildingName"){type = NavType.StringType},
                            navArgument("roomNumber"){type = NavType.StringType}
                        )
                    ){
                        RoomNumbersList(it.arguments?.get("buildingName") as String,
                            it.arguments?.get("roomNumber") as String,
                            onClick = {
                                navController.navigate(it, navOptions{
                                    popUpTo(it){inclusive=true}
                                })
                            }
                        )
                    }

                    //Courses
                    composable("courses/{departmentName}",
                        arguments = listOf(
                            navArgument("departmentName"){type = NavType.StringType}
                        )
                    ){
                        CoursesList(it.arguments?.get("departmentName") as String,
                            onClick= { navController.navigate(it, navOptions{
                                popUpTo("home"){inclusive=true}
                            }) }
                        )
                    }
                    composable("courses/{departmentName}/{courseName}",
                        arguments = listOf(
                            navArgument("departmentName"){type = NavType.StringType},
                            navArgument("courseName"){type = NavType.StringType}
                        )
                    ){
                        SpecializationsList(it.arguments?.get("departmentName") as String,
                            it.arguments?.get("courseName") as String,
                            onClick={
                                navController.navigate(it, navOptions{
                                    popUpTo(it){inclusive=true}
                                })
                            }
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun HomeView(onClick: (String) ->Unit){
    val buttonModifier = Modifier.fillMaxSize()
    val context = LocalContext.current
    Box(modifier = Modifier.background(color = Color.Black)
        .fillMaxHeight(),){

        Column(modifier = Modifier
            .background(color = Color.Black)
            .padding(10.dp)
            .fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
            val rowModifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(800.dp)
                .height(80.dp)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "LessonPlan",
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
            Row(rowModifier) {
                OutlinedButton(
                    onClick = {
                        onClick("courses/Wydział Nauk o Zdrowiu i Kulturze Fizycznej")
                        Toast.makeText(context,"Wydział Nauk o Zdrowiu i Kulturze Fizycznej", Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text(text = "Wydział Nauk o Zdrowiu i Kulturze Fizycznej")
                }
            }
            Row(rowModifier) {
                OutlinedButton(
                    onClick = {
                        onClick("courses/Wydział Nauk Technicznych i Ekonomicznych")
                        Toast.makeText(context,"Wydział Nauk Technicznych i Ekonomicznych", Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                    Text(text = "Wydział Nauk Technicznych i Ekonomicznych")
                }
            }
            Row(rowModifier) {
                OutlinedButton(
                    onClick = {
                        onClick("courses/Wydział Nauk Społecznych i Humanistycznych")
                        Toast.makeText(context,"Wydział Nauk Społecznych i Humanistycznych", Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                    Text(text = "Wydział Nauk Społecznych i Humanistycznych")
                }
            }
            Row(rowModifier) {
                OutlinedButton(
                    onClick = {
                        onClick("courses/Wychowanie fizyczne")
                        Toast.makeText(context,"Wychowanie Fizyczne", Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                    Text(text = "Wychowanie fizyczne")
                }
            }
            Row(rowModifier) {
                OutlinedButton(
                    onClick = {
                        onClick("courses/Erasmus")
                        Toast.makeText(context,"Erasmus", Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                    Text(text = "Erasmus")
                }
            }
            Row(rowModifier) {
                OutlinedButton(
                    onClick = {
                        onClick("workersGroup")
                        Toast.makeText(context,"Szukanie pracownika",Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                    Text(text = "Szukaj pracownika")
                }
            }
            Row(rowModifier) {
                OutlinedButton(
                    onClick =
                    {
                        onClick("buildings")
                        Toast.makeText(context,"Szukanie Sali",Toast.LENGTH_SHORT).show()
                    },
                    buttonModifier,
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                    Text(text = "Szukaj sali")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LessonPlanAppTheme {
    }
}

