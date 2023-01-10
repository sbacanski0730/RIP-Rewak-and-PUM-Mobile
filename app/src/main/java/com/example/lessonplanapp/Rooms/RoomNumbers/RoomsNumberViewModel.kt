package com.example.lessonplanapp.Rooms.RoomNumbers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lessonplanapp.LessonPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RoomsNumberViewModel(
    private val api: LessonPlanApi,
    private val buildingName: String,
    private val roomNumber: String
): ViewModel() {

    val state = MutableStateFlow(emptyList<RoomNumbersDataItemDto>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val numbers = api.getRoomNumber(buildingName, roomNumber)
            numbers.forEach {
                val date = LocalDateTime.parse(
                    it.timeStart.date.dropLast(7),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
                it.localDate = date.toLocalDate().toString()
                it.localTime = date.toLocalTime().toString()
            }

            state.value = numbers
        }
    }
}