package com.example.lessonplanapp.Courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val api: LessonPlanApi,
    private val departmentName: String
    ): ViewModel() {

    val state = MutableStateFlow(emptyList<CoursesDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val courses = api.getCourses(departmentName)

            state.value = courses

        }
    }
}