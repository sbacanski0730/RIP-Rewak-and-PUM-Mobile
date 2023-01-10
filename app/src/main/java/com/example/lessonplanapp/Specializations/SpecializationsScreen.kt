package com.example.lessonplanapp.Specializations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import com.example.lessonplanapp.RetrofitClient
import com.example.lessonplanapp.Rooms.SpecializationsViewModel

@Composable
fun SpecializationsList(
    department: String, course: String
) {

    val viewModel = remember {
        SpecializationsViewModel(api = RetrofitClient().api,department,course )
    }

//    val state by viewModel.state.collectAsState()

    val rowModifier = Modifier
        .width(800.dp)
        .height(80.dp)
    LazyColumn(modifier = Modifier
        .background(color = Color.Black)
        .padding(10.dp)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
//        items(state){
//            Row(rowModifier){
//                OutlinedButton(
//                    onClick = { /*TODO*/ },
//                    modifier = Modifier.fillMaxSize(),
//                    border = BorderStroke(1.dp, Color.White),
//                    shape = RoundedCornerShape(25),
//                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
//                ) {
//                    Text(text = it.email)
//                }
//            }
//        }
    }
}
