package com.example.ntiteamtestclient.data.touchDBImpl

import android.content.Context
import androidx.room.Room
import com.example.ntiteamtestclient.data.TouchDB
import com.example.ntiteamtestclient.domain.Touch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TouchDBImpl(context: Context): TouchDB {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()

    override fun getTrack(): List<Touch> {
        return db.dao().getAll().map { Touch(it.x,it.y,it.axisX,it.axisY,it.pressure) }
    }

    override fun saveTouch(touch: Touch) {
        CoroutineScope(Dispatchers.IO).launch {
            db.dao().insertAll(TouchEntity(0,touch.x,touch.y,touch.axisX,touch.axisY,touch.pressure))
        }
    }

    override fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            db.clearAllTables()
        }
    }
}