package com.example.ntiteamtestclient.data

import com.example.ntiteamtestclient.domain.Touch

interface TouchDB {
    fun getTrack(): List<Touch>
    fun saveTouch(touch: Touch)
    fun clear()
}