package com.example.lessonplanapp.Workers.WorkerGroup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WorkersGroupViewModel(
    private val api: LessonPlanApi
): ViewModel() {

    var loading = mutableStateOf(value = false)
    val state = MutableStateFlow(emptyList<WorkersGroupDataItemDto>())

    init {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val groups = api.getWorkerGroups()
            state.value = groups
            loading.value = false
        }
    }
}