package com.example.lessonplanapp.Buildings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BuildingsViewModel(private val api: LessonPlanApi): ViewModel() {

    val state = MutableStateFlow(emptyList<BuildingsDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val buildings = api.getRooms()

            state.value = buildings

        }
    }
}