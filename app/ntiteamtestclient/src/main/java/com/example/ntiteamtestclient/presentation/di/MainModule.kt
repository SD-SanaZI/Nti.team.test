package com.example.ntiteamtestclient.presentation.di

import android.content.Context
import com.example.ntiteamtestclient.data.ConnectionRepoImpl
import com.example.ntiteamtestclient.data.TouchDB
import com.example.ntiteamtestclient.data.touchDBImpl.TouchDBImpl
import com.example.ntiteamtestclient.data.TouchExecutor
import com.example.ntiteamtestclient.data.TouchExecutorImpl
import com.example.ntiteamtestclient.domain.ConnectionRepo
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideTouchExecutor(): TouchExecutor{
        return TouchExecutorImpl()
    }

    @Provides
    fun provideTouchDB(context: Context):TouchDB{
        return TouchDBImpl(context)
    }

    @Provides
    fun provideConnectionRepo(db: TouchDB, touchExecutor: TouchExecutor): ConnectionRepo{
        return ConnectionRepoImpl( db, touchExecutor)
    }
}