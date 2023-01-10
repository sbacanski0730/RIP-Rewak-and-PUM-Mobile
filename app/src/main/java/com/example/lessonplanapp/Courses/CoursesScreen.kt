package com.example.lessonplanapp.Courses

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lessonplanapp.RetrofitClient
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.White

@Composable
fun CoursesList(departmentName: String,onClick: (String) ->Unit) {
    val viewModel = remember {
        CoursesViewModel(api = RetrofitClient().api,departmentName)
    }

    val state by viewModel.state.collectAsState()

    val rowModifier = Modifier
        .width(800.dp)
        .height(80.dp)
    val context = LocalContext.current

    LessonPlanAppTheme(true) {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Black)){
            Column() {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$departmentName",
                    color = White,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
                LazyColumn(modifier = Modifier
                    .background(color = Color.Black)
                    .padding(10.dp)
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(state.sortedBy { it.name }){
                        Row(rowModifier){
                            OutlinedButton(
                                onClick =
                                {
                                    onClick("courses/$departmentName/${it.name}")
                                    Toast.makeText(context,"${it.name}",Toast.LENGTH_SHORT).show()
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
}
