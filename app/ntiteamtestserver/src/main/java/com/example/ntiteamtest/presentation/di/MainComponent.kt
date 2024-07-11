package com.example.ntiteamtest.presentation.di

import com.example.ntiteamtest.presentation.MainViewModel
import dagger.Component

@Component(
    modules = [MainModule::class]
)
interface MainComponent {
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder{
        fun build():MainComponent
    }

    companion object{
        fun create():MainComponent{
            return DaggerMainComponent.builder().build()
        }
    }

}