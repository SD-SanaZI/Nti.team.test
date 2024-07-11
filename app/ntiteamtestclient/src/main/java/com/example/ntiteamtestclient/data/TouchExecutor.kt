package com.example.ntiteamtestclient.data

import com.example.ntiteamtestclient.domain.Touch

interface TouchExecutor {
    fun execute(touch: Touch)
}