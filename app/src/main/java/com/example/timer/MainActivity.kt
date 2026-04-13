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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timer.ui.theme.DarkGreen
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
fun NavigationGraph(modifier: Modifier) {
    val navController = rememberNavController()

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
fun TimerButton(
    name: String,
    modifier: Modifier
) {
    Button(onClick = { },
        modifier = modifier
            .padding(6.dp)
            .size(170.dp, 60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0083F0),
            contentColor = Color.White
        )
    ) {
        Text(name,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp)
    }
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
            Text("00:00:00",
                fontWeight = FontWeight.W600,
                fontSize = 52.sp,
                modifier = Modifier.padding(6.dp))

            Button(onClick = {},
                modifier = Modifier
                    .padding(6.dp)
                    .size(170.dp, 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text("초기화",
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp)

            }

        }

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {

            TimerButton("중지", modifier = Modifier)
            TimerButton("시작", modifier = Modifier)

//            Button(onClick = {},
//                modifier = Modifier) {
//                Text("중지",
//                    fontWeight = FontWeight.W600,
//                    )
//            }
//            Button(onClick = {},
//                modifier = Modifier) {
//                Text("시작",
//                    fontWeight = FontWeight.W600)
//            }
        }
    }

}

@Composable
fun TimerScreen(modifier: Modifier, navController: NavController) {
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