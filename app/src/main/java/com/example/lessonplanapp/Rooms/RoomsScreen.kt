package com.example.lessonplanapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lessonplanapp.Rooms.RoomsViewModel
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.White

@Composable
fun RoomsList(buildingName: String,onClick: (String) ->Unit) {
    val viewModel = remember {
        RoomsViewModel(api = RetrofitClient().api, buildingName)
    }

    val state by viewModel.state.collectAsState()

    val rowModifier = Modifier
        .width(800.dp)
        .height(80.dp)

    LessonPlanAppTheme(true) {
        Column(modifier = Modifier
            .background(color = Color.Black)
            .padding(10.dp)
            .fillMaxSize()) {
            Row() {
                Button(onClick = {},border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) {
                }
                Text(text = buildingName, color = White)
                Button(onClick = {}) {
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                items(state.sortedBy { it.name }){
                    Row(rowModifier){
                        OutlinedButton(
                            onClick = {
                                      onClick("rooms/$buildingName/${it.name}")
                            },
                            modifier = Modifier.fillMaxSize(),
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(25),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                        ) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }

}