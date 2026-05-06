package com.example.timer.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timer.ui.theme.MyGray

@Composable
fun NavButton(
    modifier: Modifier,
    onClicked: () -> Unit,
    navText: String,
    color: Color = MyGray
) {
    TextButton(
        onClick = { onClicked() },
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            contentColor = color
        )
    ) {
        Text(
            text = navText,
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }
}