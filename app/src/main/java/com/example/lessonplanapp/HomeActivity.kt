package com.example.lessonplanapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lessonplanapp.Buildings.BuildingList
import com.example.lessonplanapp.Departments.DepartmentsList
import com.example.lessonplanapp.Rooms.RoomsList
import com.example.lessonplanapp.Workers.WorkersList
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme

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
                    composable("buildings"){
                        BuildingList(onClick = {
                            navController.navigate(it)
                        })
                    }
                    composable("rooms/{postId}", arguments = listOf(navArgument("postId"){type = NavType.IntType})){
                        RoomsList(it.arguments?.get("postId") as Int)
                    }
                    composable("workers"){
                        DepartmentsList(onClick = {
                            navController.navigate(it)
                        })
                    }
                    composable("workers/{postId}", arguments = listOf(navArgument("postId"){type = NavType.IntType})){
                        WorkersList(it.arguments?.get("postId") as Int)
                    }
                }
            }


        }
    }
}

@Composable
fun HomeView(onClick: (String) ->Unit){
    Column(modifier = Modifier
        .background(color = Color.Black)
        .padding(10.dp)
        .fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        val rowModifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(800.dp)
            .height(80.dp)
        val buttonModifier = Modifier.fillMaxSize()
        val context = LocalContext.current
        Row(rowModifier) {
            OutlinedButton(onClick = { onClick("")
                Toast.makeText(context,"TEXT", Toast.LENGTH_SHORT).show()

            },
                buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) {
                Text(text = "Wydział Nauk o zdrowiu")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { Toast.makeText(context,"TEXT2", Toast.LENGTH_SHORT).show()},
                buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Wydział Nauk Technicznych")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { Toast.makeText(context,"TEXT3", Toast.LENGTH_SHORT).show() },buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Wydział Nauk społecznych")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { /*TODO*/ },buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Wychowanie Fizyczne")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { /*TODO*/ },buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Erazmus")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { onClick("workers") },buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Szukaj pracownika")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick =
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LessonPlanAppTheme {
    }
}

