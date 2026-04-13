package com.example.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.timer.ui.theme.MyGray
import com.example.timer.ui.theme.TimerTheme
import kotlinx.coroutines.delay
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

data class State(val title: String, val showBar: Boolean)


@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
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
            }
        ) { innerPadding ->
            NavigationGraph(modifier = Modifier.padding(innerPadding),
                navController = navController)
        }
    }
}


@Serializable
object Stopwatch

@Serializable
object Timer

@Serializable
object Splash

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

@Composable
fun StopwatchButton(
    name: String,
    color: Color = Color(0xFF0083F0),
    modifier: Modifier,
    onClicked: () -> Unit
) {
    Button(onClick = { onClicked() },
        modifier = modifier
            .padding(6.dp)
            .size(170.dp, 60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {
        Text(name,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp)
    }
}

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

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
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

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
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

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1000)

        navController.navigate(Stopwatch) {
            popUpTo(Splash) {inclusive = true}
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
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