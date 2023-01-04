package com.example.lessonplanapp.Courses

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.dp
import com.example.lessonplanapp.RetrofitClient

class CoursesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Toast.makeText(this,"TAK",Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun CoursesList(onClick: (String) ->Unit) {
    val viewModel = remember {
        CoursesViewModel(api = RetrofitClient().api)
    }

    val state by viewModel.state.collectAsState()

    val rowModifier = Modifier
        .width(800.dp)
        .height(80.dp)
    val context = LocalContext.current
    LazyColumn(modifier = Modifier
        .background(color = Color.Black)
        .padding(10.dp)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(state){
            Row(rowModifier){
                OutlinedButton(
                    onClick =
                    {
                        onClick("specializations/${it.id}")
                        Toast.makeText(context,"${it.title}",Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxSize(),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text(text = it.title)
                }
            }
        }
    }
}
