package com.example.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timer.ui.theme.DarkGreen
import com.example.timer.ui.theme.TimerTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun Greeting() {
    TimerTheme {

        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCED0E0)),
            topBar = {
                TopAppBar(
                    title = {
                        Text(modifier = Modifier.fillMaxWidth(), text = "toptoptop") },
                    colors = topAppBarColors(
                        containerColor = DarkGreen,
                        titleContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            NavigationGraph(modifier = Modifier.padding(innerPadding))
        }
    }
}


@Serializable
object Stopwatch

@Serializable
object Timer

@Serializable
object Profile

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
fun NavigationGraph(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(modifier = modifier,
        navController = navController,
        startDestination = Stopwatch) {
        composable<Stopwatch> {
            StopwatchScreen(modifier = modifier, navController = navController)
        }

        composable<Timer> {
            TimerScreen(modifier = modifier, navController = navController)
        }

    }
}

@Composable
fun StopwatchScreen(modifier: Modifier, navController: NavController) {
    Button(modifier = Modifier.background(Color.White),
        onClick = {navController.navigate(Timer)}
        ) {

        Text("Timer", color = Color(0xFFA0FF4C))
    }
}

@Composable
fun TimerScreen(modifier: Modifier, navController: NavController) {
    Button(onClick = {navController.navigate(Stopwatch)}, modifier = Modifier) {
        Text("Stopwatch")
    }
}