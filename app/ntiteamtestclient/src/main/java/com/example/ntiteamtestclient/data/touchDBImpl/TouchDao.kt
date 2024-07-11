package com.example.ntiteamtestclient.data.touchDBImpl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TouchDao {
    @Query("SELECT * FROM touchEntity")
    fun getAll(): List<TouchEntity>

    @Insert
    fun insertAll(vararg touchEntity: TouchEntity)
}