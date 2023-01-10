package com.example.lessonplanapp.Workers.WorkerName

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WorkersViewModel(
    private val api: LessonPlanApi,
    private val departmentName: String,
    private val workerName: String
): ViewModel() {

    val state = MutableStateFlow(emptyList<WorkersNameDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val workers = api.getWorkerName(departmentName,workerName)
            workers.forEach {
                val date = LocalDateTime.parse(
                    it.timeStart.date.dropLast(7),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
                it.localDate = date.toLocalDate().toString()
                it.localTime = date.toLocalTime().toString()
            }

            state.value = workers
        }
    }
}