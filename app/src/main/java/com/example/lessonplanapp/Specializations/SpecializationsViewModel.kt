package com.example.lessonplanapp.Rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import com.example.lessonplanapp.Posts.Comments.PostsCommentsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SpecializationsViewModel(
    private val api: LessonPlanApi,
    private val id: Int
): ViewModel() {

    val state = MutableStateFlow(emptyList<PostsCommentsDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val rooms = api.getPostComment(id)
            state.value = rooms

        }
    }
}