package com.example.timer.features.Stopwatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.timer.ui.components.FlexButton
import com.example.timer.ui.components.NavButton
import com.example.timer.ui.components.TopBar
import com.example.timer.ui.theme.AntiqueBlue
import com.example.timer.util.route.Timer
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(modifier: Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier
        .fillMaxSize()
        .background(AntiqueBlue),
        topBar = {
            TopBar("스톱워치")
        }
    ) { innerpadding ->

//        StopwatchItems(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(innerpadding),
//            navController = navController
//        )

        var milliSeconds by remember { mutableIntStateOf(0) }
        var isRunning by remember { mutableStateOf(false) }

        LaunchedEffect(isRunning) {
            while (isRunning) {
                delay(10)
                milliSeconds += 1
            }
        }

        Box(modifier = modifier
            .fillMaxSize()
            .padding(innerpadding)
        ) {
            NavButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClicked = { navController.navigate(Timer) },
                navText = "타이머 →"
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    formatTime(milliSeconds),
                    fontWeight = FontWeight.W600,
                    fontSize = 52.sp,
                    modifier = Modifier.padding(6.dp)
                )

                FlexButton(
                    name = "초기화",
                    color = Color.Red,
                    modifier = Modifier
                ) {
                    isRunning = false
                    milliSeconds = 0
                }
            }

            Row(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                FlexButton(
                    name = "중지",
                    modifier = Modifier
                ) {
                    isRunning = false
                }

                FlexButton(
                    name = "시작",
                    modifier = Modifier
                ) {
                    isRunning = true
                }
            }
        }
    }
}

fun formatTime(milliSeconds: Int): String {
    val m = milliSeconds / 6000
    val s = (milliSeconds / 100) % 60
    val ms = milliSeconds % 100

    return "%02d:%02d:%02d".format(m, s, ms)
}

//@Composable
//fun StopwatchItems(
//    modifier: Modifier,
//    navController: NavController
//) {
//    var milliSeconds by remember { mutableIntStateOf(0) }
//    var isRunning by remember { mutableStateOf(false) }
//
//    LaunchedEffect(isRunning) {
//        while (isRunning) {
//            delay(10)
//            milliSeconds += 1
//        }
//    }
//
//    Box(modifier = modifier) {
//        NavButton(
//            modifier = Modifier.align(Alignment.TopEnd),
//            onClicked = { navController.navigate(Timer) },
//            navText = "타이머 →"
//        )
//
//        Column(
//            modifier = Modifier.align(Alignment.Center),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(formatTime(milliSeconds),
//                fontWeight = FontWeight.W600,
//                fontSize = 52.sp,
//                modifier = Modifier.padding(6.dp)
//            )
//
//            FlexButton(name = "초기화",
//                color = Color.Red,
//                modifier = Modifier
//            ) {
//                isRunning = false
//                milliSeconds = 0
//            }
//        }
//
//        Row(modifier = Modifier.align(Alignment.BottomCenter),
//            horizontalArrangement = Arrangement.spacedBy(20.dp)) {
//
//            FlexButton(name = "중지",
//                modifier = Modifier
//            ) {
//                isRunning = false
//            }
//
//            FlexButton(name = "시작",
//                modifier = Modifier
//            ) {
//                isRunning = true
//            }
//
//
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
//        }
//    }
//}