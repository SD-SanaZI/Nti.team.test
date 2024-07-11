package com.example.ntiteamtestclient.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ntiteamtestclient.domain.ConnectionRepo
import com.example.ntiteamtestclient.domain.Touch
import com.example.ntiteamtestclient.presentation.ui.theme.NtiteamtestTheme

@Composable
fun Main(viewModel: MainViewModel, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate(Screens.Config.route)
        }) {
            Text(
                text = "Config",
            )
        }
        StartStopButton(viewModel)
        Button(onClick = {
            navController.navigate(Screens.TouchList.route)
        }) {
            Text(
                text = "Полный трек"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewMain() {
    NtiteamtestTheme {
        val navController = rememberNavController()
        val connectionRepo = object : ConnectionRepo {
            override fun startServer(port: Int, host: String) {
                TODO("Not yet implemented")
            }

            override fun stopServer() {
                TODO("Not yet implemented")
            }

            override fun getTrack(): List<Touch> {
                TODO("Not yet implemented")
            }
        }
        Main(MainViewModel(connectionRepo), navController)
    }
}