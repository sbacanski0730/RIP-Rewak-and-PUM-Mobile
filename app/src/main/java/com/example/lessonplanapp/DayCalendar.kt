package com.example.lessonplanapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.example.lessonplanapp.ui.theme.LessonPlanAppTheme
import com.example.lessonplanapp.ui.theme.Purple200
import com.example.lessonplanapp.ui.theme.Purple500
import com.example.lessonplanapp.ui.theme.White
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition

@Composable
fun Day(day: WeekDay, isSelected: Boolean, onClick: (WeekDay) -> Unit) {
    LessonPlanAppTheme() {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(CutCornerShape(20))
                .background(color = if (isSelected) Purple200 else Purple500)
                .clickable(
                    enabled = day.position == WeekDayPosition.RangeDate,
                    onClick = { onClick(day) }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center)
            ){
                Text(text = day.date.dayOfWeek.toString().take(3), color = White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                Text(text = day.date.dayOfMonth.toString(), color = White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}