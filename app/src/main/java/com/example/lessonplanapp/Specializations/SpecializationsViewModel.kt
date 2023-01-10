package com.example.lessonplanapp.Rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecializationsViewModel(
    private val api: LessonPlanApi,
    private val department: String,
    private val course: String
): ViewModel() {

//    val state = MutableStateFlow(emptyList<>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
//            val rooms = api.getPostComment(id)
//            state.value = rooms

        }
    }
}