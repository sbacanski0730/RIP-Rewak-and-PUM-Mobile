package com.example.lessonplanapp.Courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import com.example.lessonplanapp.Posts.PostsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(private val api: LessonPlanApi): ViewModel() {

    val state = MutableStateFlow(emptyList<PostsDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val buildings = api.getPosts()

            state.value = buildings

        }
    }
}