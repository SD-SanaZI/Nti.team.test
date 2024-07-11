package com.example.ntiteamtestclient.data.touchDBImpl

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TouchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): TouchDao
}