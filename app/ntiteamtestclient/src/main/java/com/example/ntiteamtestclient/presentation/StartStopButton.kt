package com.example.ntiteamtestclient.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ntiteamtestclient.domain.ConnectionRepo
import com.example.ntiteamtestclient.domain.Touch
import com.example.ntiteamtestclient.presentation.ui.theme.NtiteamtestTheme

@Composable
fun StartStopButton(viewModel: MainViewModel) {
    val isServerStarted = remember { viewModel.isServerStarted }
    if (isServerStarted.value) Button(onClick = {
        viewModel.stopServer()
    }) {
        Text(text = "Stop")
    }
    else Button(onClick = {
        viewModel.startServer()
    }) {
        Text(text = "Start")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewStartStopButton() {
    NtiteamtestTheme {
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
        StartStopButton(MainViewModel(connectionRepo))
    }
}