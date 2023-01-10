package com.example.lessonplanapp.Rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val api: LessonPlanApi,
    private val name: String
): ViewModel() {

    val state = MutableStateFlow(emptyList<RoomsDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val rooms = api.getBuildingNames(name)
            state.value = rooms

        }
    }
}