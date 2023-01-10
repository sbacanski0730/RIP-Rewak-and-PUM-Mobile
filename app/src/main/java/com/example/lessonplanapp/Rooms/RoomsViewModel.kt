package com.example.lessonplanapp.Rooms

import androidx.compose.runtime.mutableStateOf
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

    var loading = mutableStateOf(value = false)
    val state = MutableStateFlow(emptyList<RoomsDataItemDto>())

    init {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val rooms = api.getBuildingNames(name)
            state.value = rooms
            loading.value = false
        }
    }
}