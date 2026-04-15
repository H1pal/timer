package com.example.timer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.timer.ui.theme.MyGray
import com.example.timer.util.navigation.Stopwatch

@Composable
fun TimerScreen(modifier: Modifier, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        TextButton(onClick = {navController.navigate(Stopwatch)},
            modifier = Modifier,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MyGray
            )
        ) {
            Text(text = "← 스톱워치",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp))
        }

        Text("타이머 화면 입니다",
            fontWeight = FontWeight.W600,
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        Row(modifier = Modifier.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(20.dp)) {

            Button(onClick = { },
                modifier = Modifier
                    .padding(6.dp)
                    .size(170.dp, 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0083F0),
                    contentColor = Color.White
                )
            ) {
                Text("중지",
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp)
            }

            Button(onClick = { },
                modifier = Modifier
                    .padding(6.dp)
                    .size(170.dp, 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0083F0),
                    contentColor = Color.White
                )
            ) {
                Text("시작",
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp)
            }
        }
    }
}