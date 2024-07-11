package com.example.ntiteamtestclient.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ntiteamtestclient.domain.ConnectionRepo
import com.example.ntiteamtestclient.domain.Touch

class MainViewModel(private val connectionRepo: ConnectionRepo) : ViewModel() {
    private var port: Int = 80
    private var host: String = "127.0.0.1"
    var isServerStarted = mutableStateOf(false)

    class MainViewModelFactory(
        private val connectionRepo: ConnectionRepo
    ):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(connectionRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun startServer(){
        connectionRepo.startServer(port,host)
        isServerStarted.value = true
    }

    fun stopServer(){
        connectionRepo.stopServer()
        isServerStarted.value = false
    }

    fun getTrack(): List<Touch>{
        return connectionRepo.getTrack()
    }

    fun setConfig(port: Int, host: String){
        this.port = port
        this.host = host
    }

    fun getPort() = port

    fun getHost() = host
}