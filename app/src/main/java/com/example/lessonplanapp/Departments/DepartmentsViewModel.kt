package com.example.lessonplanapp.Departments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DepartmentsViewModel(
    private val api: LessonPlanApi,
    private val workersGroups: String
    ): ViewModel() {

    val state = MutableStateFlow(emptyList<DepartmentDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val departments = api.getDepartmentName(workersGroups,"ASC")

            state.value = departments

        }
    }
}