package com.example.timer.features.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.timer.R
import com.example.timer.ui.theme.AntiqueBlue
import com.example.timer.util.route.Splash
import com.example.timer.util.route.Stopwatch
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(AntiqueBlue)
    ) { innerPadding ->
//        SplashItems(
//            modifier = Modifier
//            .fillMaxSize()
//            .padding(innerPadding),
//            navController = navController
//        )

        LaunchedEffect(Unit) {
            delay(1000)

            navController.navigate(Stopwatch) {
                popUpTo(Splash) {inclusive = true}
            }
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally )
            {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null
                )
                Text("Timer",
                    fontSize = 40.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }

    }
}

//@Composable
//fun SplashItems(
//    modifier: Modifier,
//    navController: NavController
//) {
//    LaunchedEffect(Unit) {
//        delay(1000)
//
//        navController.navigate(Stopwatch) {
//            popUpTo(Splash) {inclusive = true}
//        }
//    }
//
//    Box(contentAlignment = Alignment.Center,
//        modifier = modifier
//    ) {
//        Column(
//            modifier = modifier,
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                modifier = Modifier.size(200.dp),
//                painter = painterResource(id = R.drawable.clock),
//                contentDescription = null
//            )
//            Text("Timer",
//                fontSize = 40.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.W600,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(top = 30.dp)
//            )
//        }
//    }
//}