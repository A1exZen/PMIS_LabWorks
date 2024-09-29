package com.example.lab1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Patients() {
    val patients = listOf(
        Patient("Алексей Иванов", "Грипп"),
        Patient("Мария Петрова", "Астма"),
        Patient("Иван Сидоров", "Диабет"),
        Patient("Елена Смирнова", "Гипертония"),
        Patient("Дмитрий Кузнецов", "Аллергия"),
        Patient("Ольга Попова", "Аллергия"),
        Patient("Сергей Лебедев", "Грипп"),
        Patient("Анна Козлова", "Мигрень"),
        Patient("Николай Новиков", "Грипп"),
        Patient("Николай Панфиленко", "Артрит"),
        Patient("Татьяна Федорова", "Артрит")
    )
    val groups = patients.groupBy { it.diagnosis }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        groups.forEach { (diagnosis, patients) ->
            stickyHeader {
                Text(text = diagnosis,
                    fontSize = 28.sp,
                    color = Color.White,
                    modifier =
                    Modifier.background(Color.Black).padding(5.dp).fillMaxWidth()
                )
            }
            items(patients) { patient ->
                Text(patient.name, Modifier.padding(5.dp), fontSize =
                28.sp)
            }
        }
    }
}

data class Patient(val name: String, val diagnosis: String)
