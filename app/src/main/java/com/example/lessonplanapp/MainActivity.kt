package com.example.lessonplanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            LessonPlanAppTheme() {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    HomeView()
                }
            }
        }
    }
}

@Composable
private fun HomeView(){
    Column(modifier = Modifier.padding(10.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        val rowModifier = Modifier.align(Alignment.CenterHorizontally).width(800.dp).height(80.dp)
        val buttonModifier = Modifier.fillMaxSize()
        Row(rowModifier) {
            OutlinedButton(onClick = { /*TODO*/ },
                buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) {
               Text(text = "Wydział Nauk o zdrowiu")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { /*TODO*/ },
                buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Wydział Nauk Technicznych")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { /*TODO*/ },buttonModifier,
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
            OutlinedButton(onClick = { /*TODO*/ },buttonModifier,
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                Text(text = "Szukaj pracownika")
            }
        }
        Row(rowModifier) {
            OutlinedButton(onClick = { /*TODO*/ },buttonModifier,
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

