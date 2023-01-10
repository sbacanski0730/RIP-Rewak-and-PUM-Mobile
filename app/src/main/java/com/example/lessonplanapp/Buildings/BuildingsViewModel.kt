package com.example.lessonplanapp.Buildings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.CircularProgresBar
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BuildingsViewModel(private val api: LessonPlanApi): ViewModel() {
    var loading = mutableStateOf(value = false)

    val state = MutableStateFlow(emptyList<BuildingsDataItemDto>())

    init {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val buildings = api.getRooms()

            state.value = buildings
            loading.value = false
        }
    }
}