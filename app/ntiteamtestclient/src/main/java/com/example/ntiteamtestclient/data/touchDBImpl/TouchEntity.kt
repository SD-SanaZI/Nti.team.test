package com.example.ntiteamtestclient.data.touchDBImpl

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TouchEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid")  val uid: Int,
    @ColumnInfo(name = "x") val x: Float = 0f,
    @ColumnInfo(name = "y") val y: Float = 0f,
    @ColumnInfo(name = "axisX") val axisX: Float = 0f,
    @ColumnInfo(name = "axisY") val axisY: Float = 0f,
    @ColumnInfo(name = "pressure") val pressure: Float = 0f
)

