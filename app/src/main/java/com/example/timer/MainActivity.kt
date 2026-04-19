package com.example.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

    TimerTheme {
        NavigationGraph( navController = navController)
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