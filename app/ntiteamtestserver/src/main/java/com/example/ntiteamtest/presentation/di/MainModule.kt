package com.example.ntiteamtest.presentation.di

import android.content.Context
import com.example.ntiteamtest.data.ServerRepoImpl
import com.example.ntiteamtest.domain.ServerRepo
import dagger.Module
import dagger.Provides
import java.io.File

@Module
class MainModule {

    @Provides
    fun provideServerRepo(): ServerRepo {
        return ServerRepoImpl()
    }
}