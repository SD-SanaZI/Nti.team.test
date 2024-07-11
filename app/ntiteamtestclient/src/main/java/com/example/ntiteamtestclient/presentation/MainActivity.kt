package com.example.ntiteamtestclient.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.ntiteamtestclient.domain.ConnectionRepo
import com.example.ntiteamtestclient.presentation.di.MainComponent
import com.example.ntiteamtestclient.presentation.ui.theme.NtiteamtestTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var connectionRepo: ConnectionRepo
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(connectionRepo)
        )[MainViewModel::class.java]
    }
    private var component: MainComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = MainComponent.create(applicationContext)
        component?.inject(this)
        setContent {
            NtiteamtestTheme {
                // A surface container using the 'background' color from the theme
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

    override fun onDestroy() {
        component = null
        super.onDestroy()
    }
}

//    Text(
//    text = "Hello Android!",
//    modifier = Modifier
//    .fillMaxSize()
//    .clickable {
//        try {
//            // send message to websocket server
//            webSocketClient.sendMessage("Hello 1")
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.d("Error", e.toString())
//            Toast
//                .makeText(this, e.toString(), Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//    )
//
//override fun onDestroy() {
//    super.onDestroy()
//    // close websocket connection
//    webSocketClient.close()
//}
//
//private fun createWebSocketClient() {
//    val serverUri = URI(getString(R.string.server_url))
//    webSocketClient = ChatWebSocketClient(serverUri) { message ->
//        // display incoming message in ListView
//        runOnUiThread {
//            run {
//                val _item = HashMap<String, Any>()
//                _item["message"] = message
//                AL.add(_item)
//            }
//            Log.d("listview", AL.toString())
//        }
//    }
//}