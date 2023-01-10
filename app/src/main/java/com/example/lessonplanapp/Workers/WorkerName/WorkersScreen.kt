package com.example.lessonplanapp.Workers.WorkerName

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.lessonplanapp.Day
import com.example.lessonplanapp.RetrofitClient
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.White
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.*
import kotlinx.coroutines.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

@OptIn(DelicateCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkersList(departmentName: String, workerName: String) {

    val viewModel = remember {
        WorkersViewModel(api = RetrofitClient().api, departmentName, workerName)
    }

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

    LessonPlanAppTheme(true){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)) {
            Column(Modifier.fillMaxWidth()) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$departmentName / $workerName",
                    color = White,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
                Button(onClick = {

                }) {

                }
                WeekCalendar(
                    Modifier.fillMaxSize(),
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
                        show(state = state, day = it.days)
                    }
                )
            }

        }
    }
}

@Composable
fun show(state: List<WorkersNameDataItemDto>, day: List<WeekDay>){
    LessonPlanAppTheme() {
        LazyColumn(){
            state.sortedBy { it.localDate }.groupBy { it.localDate }.forEach() {
                if (it.key in day[0].date.toString() .. day[6].date.toString()){
                    item {
                        Column(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp)) {
                            Row() {
                                Text(
                                    text = it.key,
                                    fontSize = 20.sp,
                                    color = White
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .border(2.dp, color = Color.DarkGray)
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    it.value.sortedBy { it.localTime }.forEach() {
                                        Row() {
                                            Column(modifier = Modifier
                                                .fillMaxWidth(0.3f)
                                                .border(
                                                    width = 1.dp, color = Color.LightGray
                                                )) {
                                                Text(text = it.localTime,fontSize = 20.sp,color = White)
                                                Text(text = it.room,fontSize = 20.sp,color = White)

                                            }
                                            Column() {
                                                Text(text = it.group,fontSize = 20.sp,color = White)
                                                Text(text = it.subject,fontSize = 20.sp,color = White)

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


