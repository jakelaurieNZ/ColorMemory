package com.jakelaurie.colormemory.di.component

import com.jakelaurie.colormemory.ColorApplication
import com.jakelaurie.colormemory.di.module.AppModule

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class
])
interface AppComponent {
    fun inject(app: ColorApplication)
}