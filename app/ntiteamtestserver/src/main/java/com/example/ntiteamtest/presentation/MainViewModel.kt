package com.example.ntiteamtest.presentation

import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntiteamtest.domain.ServerRepo
import com.example.ntiteamtest.presentation.di.MainComponent
import com.google.gson.Gson
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

class MainViewModel : ViewModel() {
    private var lastRequestTime = getTimeMillis()
    private var sendOnSec: Int = 10
    private var port: Int = 80
    @Inject
    lateinit var serverRepo: ServerRepo
    private val component: MainComponent = MainComponent.create()

    init {
        component.inject(this)
    }

    fun onViewTouch(event: MotionEvent) {
        if (serverRepo.isServerStarted() && getTimeMillis() - lastRequestTime > 1000/sendOnSec) {
            val x = event.x
            val y = event.y
            val axisX = event.touchMajor
            val axisY = event.touchMinor
            val pressure = event.pressure
            val touch = Touch(x, y, axisX, axisY, pressure)
            viewModelScope.launch {
                serverRepo.sendMsg(Gson().toJson(touch))
            }
            lastRequestTime = getTimeMillis()
        }
    }

    fun setConfig(sendOnSec: Int, port: Int) {
        this.port = port
        this.sendOnSec = sendOnSec
    }

    fun getSendOnSec() = sendOnSec

    fun getPort() = port

    fun startServer() {
        viewModelScope.launch {
            serverRepo.startServer(port)
        }
    }

    fun stopServer() {
        viewModelScope.launch {
            serverRepo.stopServer()
        }
    }
}

data class Touch(
    val x: Float = 0f,
    val y: Float = 0f,
    val axisX: Float = 0f,
    val axisY: Float = 0f,
    val pressure: Float = 0f
): Serializable