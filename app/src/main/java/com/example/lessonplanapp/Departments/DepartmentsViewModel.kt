package com.example.lessonplanapp.Departments

import androidx.compose.runtime.mutableStateOf
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

    var loading = mutableStateOf(value = false)
    val state = MutableStateFlow(emptyList<DepartmentDataItemDto>())

    init {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val departments = api.getDepartmentName(workersGroups,"ASC")

            state.value = departments
            loading.value=false
        }
    }
}