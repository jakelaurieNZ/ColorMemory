package com.jakelaurie.colormemory.di.component

import com.jakelaurie.colormemory.ColorApplication
import com.jakelaurie.colormemory.di.module.AppModule
import com.jakelaurie.colormemory.di.module.BuildersModule
import com.jakelaurie.colormemory.di.module.DataModule

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton @Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            BuildersModule::class,
            DataModule::class
        ]
)
interface AppComponent : AndroidInjector<ColorApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ColorApplication>()
}