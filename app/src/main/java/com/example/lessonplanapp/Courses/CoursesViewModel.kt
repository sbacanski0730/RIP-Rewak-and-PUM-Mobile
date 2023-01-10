package com.example.lessonplanapp.Courses

import androidx.compose.runtime.mutableStateOf
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

    var loading = mutableStateOf(value = false)
    val state = MutableStateFlow(emptyList<CoursesDataItemDto>())

    init {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val courses = api.getCourses(departmentName)

            state.value = courses
            loading.value = false
        }
    }
}