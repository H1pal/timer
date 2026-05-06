package com.example.timer.features.Timer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.timer.ui.components.FlexButton
import com.example.timer.ui.components.NavButton
import com.example.timer.ui.components.TopBar
import com.example.timer.ui.theme.AntiqueBlue
import com.example.timer.util.route.Stopwatch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(modifier: Modifier, navController: NavController) {
    Scaffold(modifier = modifier
        .fillMaxSize()
        .background(AntiqueBlue),
        topBar =  {
            TopBar(
                titleText = "타이머"
            )
        }
    ) { innerPadding ->

//        TimerItems(modifier = Modifier
//            .fillMaxSize()
//            .padding(innerPadding),
//            navController = navController
//        )

        Box(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            NavButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClicked = { navController.navigate(Stopwatch) },
                navText = "← 스톱워치"
            )

            Text("타이머 화면 입니다",
                fontWeight = FontWeight.W600,
                fontSize = 26.sp,
                modifier = Modifier.align(Alignment.Center)
            )

            Row(modifier = Modifier.align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                FlexButton(name = "중지",
                    modifier = Modifier) {}

                FlexButton(name = "시작",
                    modifier = Modifier) {}
            }
        }
    }
}

//@Composable
//fun TimerItems(
//    modifier: Modifier,
//    navController: NavController
//) {
//    Box(modifier = modifier) {
//        NavButton(
//            modifier = Modifier.align(Alignment.TopStart),
//            onClicked = { navController.navigate(Stopwatch) },
//            navText = "← 스톱워치"
//        )
//
//        Text("타이머 화면 입니다",
//            fontWeight = FontWeight.W600,
//            fontSize = 26.sp,
//            modifier = Modifier.align(Alignment.Center)
//        )
//
//        Row(modifier = Modifier.align(Alignment.BottomCenter),
//            horizontalArrangement = Arrangement.spacedBy(20.dp)) {
//
//            FlexButton(name = "중지",
//                modifier = Modifier) {}
//
//            FlexButton(name = "시작",
//                modifier = Modifier) {}
//
//        }
//    }
//}