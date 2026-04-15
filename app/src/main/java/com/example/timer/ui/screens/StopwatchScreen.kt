package com.example.timer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.timer.ui.components.StopwatchButton
import com.example.timer.ui.theme.MyGray
import com.example.timer.util.navigation.Timer
import kotlinx.coroutines.delay


fun formatTime(seconds: Int): String {
    val h = seconds / 3600
    val m = (seconds / 60) % 60
    val s = seconds % 60

    return "%02d:%02d:%02d".format(h, m, s)
}

@Composable
fun StopwatchScreen(modifier: Modifier, navController: NavController) {
//    Button(modifier = Modifier,
//        colors = ButtonDefaults.buttonColors(
//            MaterialTheme.colorScheme.primaryContainer,
//            MaterialTheme.colorScheme.onPrimaryContainer
//        ),
//        onClick = {navController.navigate(Timer)}
//        ) {
//
//        Text("Timer", color = MyGray)
//    }
    var seconds by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000)
            seconds += 1
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        TextButton(onClick = {navController.navigate(Timer)},
            modifier = Modifier.align(Alignment.TopEnd),
            colors = ButtonDefaults.textButtonColors(
                contentColor = MyGray
            )
        ) {
            Text(text = "타이머 →",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(formatTime(seconds),
                fontWeight = FontWeight.W600,
                fontSize = 52.sp,
                modifier = Modifier.padding(6.dp))

            StopwatchButton(name = "초기화",
                color = Color.Red,
                modifier = Modifier) {
                isRunning = false
                seconds = 0
            }
//            Button(onClick = {
//                isRunning = false
//                seconds = 0
//            },
//                modifier = Modifier
//                    .padding(6.dp)
//                    .size(170.dp, 60.dp),
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Red,
//                    contentColor = Color.White
//                )
//            ) {
//                Text("초기화",
//                    fontWeight = FontWeight.W600,
//                    fontSize = 24.sp)
//            }

        }

        Row(modifier = Modifier.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            StopwatchButton(name = "중지",
                modifier = Modifier) {
                isRunning = false
            }
            StopwatchButton(name = "시작",
                modifier = Modifier) {
                isRunning = true
            }


//            Button(onClick = {
//                isRunning = false
//            },
//                modifier = Modifier
//                    .padding(6.dp)
//                    .size(170.dp, 60.dp),
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF0083F0),
//                    contentColor = Color.White
//                )
//            ) {
//                Text("중지",
//                    fontWeight = FontWeight.W600,
//                    fontSize = 24.sp)
//            }
//
//            Button(onClick = {
//                isRunning = true
//            },
//                modifier = Modifier
//                    .padding(6.dp)
//                    .size(170.dp, 60.dp),
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF0083F0),
//                    contentColor = Color.White
//                )
//            ) {
//                Text("시작",
//                    fontWeight = FontWeight.W600,
//                    fontSize = 24.sp)
//            }
        }
    }

}