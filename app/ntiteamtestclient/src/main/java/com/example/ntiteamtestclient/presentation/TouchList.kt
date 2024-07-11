package com.example.ntiteamtestclient.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ntiteamtestclient.domain.ConnectionRepo
import com.example.ntiteamtestclient.domain.Touch
import com.example.ntiteamtestclient.presentation.ui.theme.NtiteamtestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun TouchList(viewModel: MainViewModel, navController: NavController) {
    val list = remember { mutableStateListOf<Touch>() }
    LaunchedEffect(CoroutineScope(Dispatchers.IO)) {
        withContext(Dispatchers.IO) {
            viewModel.getTrack().forEach {
                list.add(it)
            }
        }
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            list.forEach {
                Box(
                    modifier = Modifier
                        .padding(top = it.x.dp, start = it.y.dp)
                        .height(it.axisX.dp)
                        .width(it.axisY.dp)
                        .background(Color.Blue.copy(alpha = it.pressure), RoundedCornerShape(100))
                )
            }
        }
        Button(onClick = {
            navController.navigate(Screens.Main.route)
        }) {
            Text(text = "Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewTouchList() {
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
        TouchList(MainViewModel(connectionRepo), navController)
    }
}