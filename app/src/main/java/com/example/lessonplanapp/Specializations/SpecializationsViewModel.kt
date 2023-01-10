package com.example.lessonplanapp.Specializations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SpecializationsViewModel(
    private val api: LessonPlanApi,
    private val department: String,
    private val course: String
): ViewModel() {

    val state = MutableStateFlow(emptyList<SpecializationDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val specialization = api.getCoursesName(department,course)

            specialization.forEach {
                val date = LocalDateTime.parse(
                    it.timeStart.date.dropLast(7),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
                it.localDate = date.toLocalDate().toString()
                it.localTime = date.toLocalTime().toString()
            }

            state.value = specialization

        }
    }
}