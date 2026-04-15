package com.example.timer.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopwatchButton(
    name: String,
    color: Color = Color(0xFF0083F0),
    modifier: Modifier,
    onClicked: () -> Unit
) {
    Button(onClick = { onClicked() },
        modifier = modifier
            .padding(6.dp)
            .size(170.dp, 60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {
        Text(name,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp)
    }
}