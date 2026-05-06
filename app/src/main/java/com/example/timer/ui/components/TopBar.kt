package com.example.timer.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.timer.ui.theme.MyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titleText: String,
    color: Color = MyBlue
) {
    TopAppBar(
        title = {
            Text(
                text = titleText,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp
            )
        },
        colors = topAppBarColors(
            containerColor = color,
            titleContentColor = Color.White
        )
    )
}