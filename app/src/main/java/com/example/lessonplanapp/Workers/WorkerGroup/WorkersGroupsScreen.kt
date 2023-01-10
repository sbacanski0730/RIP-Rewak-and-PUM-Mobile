package com.example.lessonplanapp.Workers.WorkerGroup

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lessonplanapp.CircularProgresBar
import com.example.lessonplanapp.RetrofitClient
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.White

@Composable
fun WorkersGroupList(
    onClick: (String) ->Unit
) {

    val viewModel = remember {
        WorkersGroupViewModel(api = RetrofitClient().api)
    }

    val loading = viewModel.loading.value
    val state by viewModel.state.collectAsState()

    val rowModifier = Modifier
        .width(800.dp)
        .height(80.dp)


    LessonPlanAppTheme(true) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)){

            Column(
                modifier = Modifier
                    .background(color = Color.Black)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(25),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        onClick = { onClick("home") }
                    ) {
                        Text(text = "Home")
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Szukanie Pracownika",
                        color = White,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                }
                CircularProgresBar(isDisplayed = loading)
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(state.sortedByDescending { it.name }){
                        Row(rowModifier){
                            OutlinedButton(
                                onClick = { onClick("departments/${it.name}") },
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
}
