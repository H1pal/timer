package com.example.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.timer.ui.screens.SplashScreen
import com.example.timer.ui.screens.StopwatchScreen
import com.example.timer.ui.screens.TimerScreen
import com.example.timer.ui.theme.TimerTheme
import com.example.timer.util.navigation.Splash
import com.example.timer.util.navigation.Stopwatch
import com.example.timer.util.navigation.Timer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}

data class State(val title: String, val showBar: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val topBarState = when {
        currentBackStackEntry?.destination?.hasRoute<Timer>() == true ->
            State("타이머", true)
        currentBackStackEntry?.destination?.hasRoute<Stopwatch>() == true ->
            State("스톱워치", true)
        else -> State("Timer", false)
    }

    TimerTheme {

        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCED0E0)),
            topBar = {
                if (topBarState.showBar) {
                    TopAppBar(
                        title = {
                            Text(text = topBarState.title,
                                fontWeight = FontWeight.W600,
                                fontSize = 24.sp)
                        },
                        colors = topAppBarColors(
                            containerColor = Color(0xFF0083F0),
                            titleContentColor = Color.White
                        )
                    )
                }
            },
            bottomBar = {

            }
        ) { innerPadding ->
            NavigationGraph(modifier = Modifier.padding(innerPadding),
                navController = navController)
        }
    }
}

//enum class Nav_Route(routeName : String, description: String, btnColor: Color) {
//    HOME("HOME", "메인 화면", Color(0xFFFFFFFF)),
//    SETTING("SETTING", "셋팅 화면", Color(0xFFFFFFFF)),
//    PROFILE("PROFILE", "프로필 화면", Color(0xFFFFFFFF)),
//}

//class RouteAction(navHostController: NavHostController) {
//    val navTo: (Nav_Route) -> Unit = { route ->
//        navHostController.navigate(route.name )
//    }
//
//    val goBack: () -> Unit = {
//        navHostController.navigateUp()
//    }
//}

@Composable
fun NavigationGraph(modifier: Modifier, navController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Splash
    ) {
        composable<Stopwatch> {
            StopwatchScreen(modifier = modifier, navController = navController)
        }

        composable<Timer> {
            TimerScreen(modifier = modifier, navController = navController)
        }

        composable<Splash> {
            SplashScreen(navController = navController)
        }
    }
}