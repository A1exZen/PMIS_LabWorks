package com.example.lab1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home() {
    var text by rememberSaveable { mutableStateOf("") }
    val initialFullName = stringResource(id = R.string.full_name)
    var fullName by rememberSaveable { mutableStateOf(initialFullName) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "$fullName",
                modifier = Modifier.padding(16.dp),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.android_studio_logo),
                modifier = Modifier.height(200.dp),
                contentDescription = "Android Image"
            )
        }
        item {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(text = "Введите имя:") },
                placeholder = { Text(text = "Иванов И. И.") },
                modifier = Modifier
                    .padding(2.dp, 50.dp, 2.dp, 20.dp)
                    .fillMaxWidth()
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
            ) {
                Button(onClick = { fullName = text }, shape = CutCornerShape(10)) {
                    Text("Ввод")
                }
                FilledTonalButton(onClick = { text = "" }, shape = CutCornerShape(10)) {
                    Text("Очистить")
                }
            }
        }
    }
}