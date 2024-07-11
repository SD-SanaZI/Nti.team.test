package com.example.ntiteamtestclient.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Config(viewModel: MainViewModel, navController: NavController) {
    val port = remember { mutableStateOf(viewModel.getPort().toString()) }
    var hostList = viewModel.getHost().split(".")
    if (hostList.size < 3) hostList = listOf("127", "0", "0", "1")
    val host1 = remember { mutableStateOf(hostList[0]) }
    val host2 = remember { mutableStateOf(hostList[1]) }
    val host3 = remember { mutableStateOf(hostList[2]) }
    val host4 = remember { mutableStateOf(hostList[3]) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Port", Modifier.padding(bottom = 16.dp))
            TextField(value = port.value, onValueChange = { port.value = it })
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Host")
            Row(
                Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                TextField(value = host1.value, onValueChange = {host1.value = it}, Modifier.width(70.dp))
                Text(text = ".", Modifier.padding(horizontal = 4.dp))
                TextField(value = host2.value, onValueChange = {host2.value = it}, Modifier.width(70.dp))
                Text(text = ".", Modifier.padding(horizontal = 4.dp))
                TextField(value = host3.value, onValueChange = {host3.value = it}, Modifier.width(70.dp))
                Text(text = ".", Modifier.padding(horizontal = 4.dp))
                TextField(value = host4.value, onValueChange = {host4.value = it}, Modifier.width(70.dp))
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                navController.navigate(Screens.Main.route)
            }) {
                Text(text = "Cansel")
            }
            Button(onClick = {
                if (port.value == "") port.value = viewModel.getPort().toString()
                if (host1.value == "") host1.value = hostList[0]
                if (host2.value == "") host2.value = hostList[1]
                if (host3.value == "") host3.value = hostList[2]
                if (host4.value == "") host4.value = hostList[3]
                viewModel.setConfig(
                    port.value.toInt(),
                    "${host1.value}.${host2.value}.${host3.value}.${host4.value}"
                )
                navController.navigate(Screens.Main.route)
            }) {
                Text(text = "Save")
            }
        }
    }
}