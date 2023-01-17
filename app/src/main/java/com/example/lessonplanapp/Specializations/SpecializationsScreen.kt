package com.example.lessonplanapp.Specializations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.lessonplanapp.CircularProgresBar
import com.example.lessonplanapp.Day
import com.example.lessonplanapp.RetrofitClient
import com.example.lessonplanapp.Rooms.RoomNumbers.RoomNumbersDataItemDto
import com.example.lessonplanapp.Rooms.RoomNumbers.showRoomData
import com.example.lessonplanapp.Rooms.RoomNumbers.showRoomsDay
import com.example.lessonplanapp.Rooms.RoomNumbers.showRoomsWeek
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.White
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun SpecializationsList(
    department: String, course: String,
    onClick: (String) ->Unit
) {

    val viewModel = remember {
        SpecializationsViewModel(api = RetrofitClient().api,department,course )
    }

    val loading = viewModel.loading.value
    val state by viewModel.state.collectAsState()

    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() } // Adjust as needed
    val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() } // Adjust as needed
    var firstDayOfWeek = DayOfWeek.MONDAY // Available from the library
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    var state1 = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek,

        )

    fun scrool(){
        runBlocking {
            launch {
                state1.scrollToWeek(currentDate)
            }
        }
    }

    LessonPlanAppTheme(true){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)) {
            Column(Modifier.fillMaxWidth()) {
                Row(
//                    Modifier.heightIn(10.dp, 20.dp)
                ) {
                    Column() {
                        Button(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(25),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            onClick = {
                                onClick("home")
                            }
                        ) {
                            Text(text = "Home")
                        }
                        Button(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(25),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            onClick = {
                                scrool()
                            }
                        ) {
                            Text(text = "Today")
                        }
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "$department / $course",
                        color = White,
                        textAlign = TextAlign.Center,
                        fontSize = 6.em
                    )
                }
                WeekCalendar(
                    Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    state = state1,
                    dayContent = { day ->
                        Day(day, isSelected = selectedDate == day.date) { day ->
                            selectedDate = if (selectedDate == day.date) null else day.date
                        }
                    },
                    weekHeader = {
                        Text(text = it.days[0].date.month.toString(), textAlign = TextAlign.Center, color = White)
                    },
                    weekFooter = {
                        CircularProgresBar(isDisplayed = loading)
                        if (selectedDate == null || state1.isScrollInProgress){
                            selectedDate = null
                            showCoursesWeek(state = state, day = it.days)
                        }
                        else{
                            showCoursesDay(state, selectedDate!!)
                        }
                    }
                )
            }

        }
    }
}

@Composable
fun showCoursesWeek(state: List<SpecializationDataItemDto>, day: List<WeekDay>){
    var isShowed = false

    LessonPlanAppTheme() {
        LazyColumn(){
            state.sortedBy { it.localDate }.groupBy { it.localDate }.forEach() {
                if (it.key in day[0].date.toString() .. day[6].date.toString()){
                    item {
                        showCoursesData(it)
                    }
                    isShowed = true
                }
            }
            if (!isShowed){
                item {
                    Text(text = "BRAK ZAJĘĆ",color= White, textAlign = TextAlign.Center, fontSize = 5.em, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun showCoursesDay(state: List<SpecializationDataItemDto>, date: LocalDate){
    var isShowed = false

    LessonPlanAppTheme() {
        LazyColumn(){
            state.sortedBy { it.localDate }.groupBy { it.localDate }.forEach() {
                if (it.key == date.toString()){
                    item {
                        showCoursesData(item = it)
                    }
                    isShowed = true
                }
            }
            if (!isShowed){
                item {
                    Text(text = "BRAK ZAJĘĆ",color= White, textAlign = TextAlign.Center, fontSize = 5.em, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun showCoursesData(item: Map.Entry<String, List<SpecializationDataItemDto>>){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.key,
                fontSize = 25.sp,
                color = White,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .border(2.dp, color = Color.DarkGray)
                .fillMaxWidth()
        ) {
            Column() {
                item.value.sortedBy { it.localTime }.forEach() {
                    Row(modifier = Modifier
                        .border(width = 1.dp, color = Color.LightGray)
                        .fillMaxWidth()
                    ){
                        Column(modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .border(
                                width = 1.dp, color = Color.LightGray
                            ),
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Text(text = it.localTime,fontSize = 20.sp,color = White)
                            Text(text = it.room,fontSize = 20.sp,color = White)

                        }
                        Column() {
                            Text(text = it.profesor,fontSize = 20.sp,color = White)
                            Text(text = "${it.group} / ${it.subject}",fontSize = 20.sp,color = White)
                        }
                    }
                }
            }
        }
    }
}
