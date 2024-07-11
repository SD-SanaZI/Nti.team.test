package com.example.ntiteamtestclient.presentation.di

import android.content.Context
import com.example.ntiteamtestclient.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [MainModule::class]
)
interface MainComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{
        fun mainModule(@BindsInstance context: Context):Builder
        fun build():MainComponent
    }

    companion object{
        fun create(context: Context):MainComponent{
            return DaggerMainComponent.builder().mainModule(context).build()
        }
    }

}