package com.example.ntiteamtest.presentation

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ntiteamtest.presentation.ui.theme.NtiteamtestTheme


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NtiteamtestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { viewModel.onViewTouch(it) }
        return super.onTouchEvent(event)
    }
}

@Composable
fun Greeting(viewModel: MainViewModel, navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            navController.navigate(Screens.Config.route)
        }) {
            Text(text = "Config")
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.startServer()
            }) {
                Text(text = "Start")
            }
            Button(onClick = {
                viewModel.stopServer()
            }) {
                Text(text = "Stop")
            }
        }
    }
}

@Composable
fun Config(viewModel: MainViewModel, navController: NavController) {
    val port = remember { mutableStateOf(viewModel.getPort().toString()) }
    val sendOnSec = remember { mutableStateOf(viewModel.getSendOnSec().toString()) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Port")
            TextField(value = port.value, onValueChange = { port.value = it })
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Считываний в секунду")
            TextField(value = sendOnSec.value, onValueChange = { sendOnSec.value = it })
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                navController.navigate(Screens.Greeting.route)
            }) {
                Text(text = "Cancel")
            }
            Button(onClick = {
                viewModel.setConfig(
                    sendOnSec.value.toIntOrNull() ?: viewModel.getPort(),
                    port.value.toIntOrNull()?:viewModel.getSendOnSec()
                )
                navController.navigate(Screens.Greeting.route)
            }) {
                Text(text = "Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NtiteamtestTheme {
        val navController = rememberNavController()
        Greeting(MainViewModel(), navController)
    }
}