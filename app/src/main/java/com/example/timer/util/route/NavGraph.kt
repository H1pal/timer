package com.example.timer.util.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.timer.features.splashDp.SplashScreen
import com.example.timer.features.stopwatchDp.StopwatchScreen
import com.example.timer.features.timerDp.TimerScreen

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Stopwatch> {
            StopwatchScreen(modifier = Modifier, navController = navController)
        }

        composable<Timer> {
            TimerScreen(modifier = Modifier, navController = navController)
        }

        composable<Splash> {
            SplashScreen(navController = navController)
        }
    }
}