package com.jakelaurie.colormemory.di.module

import com.jakelaurie.colormemory.ColorApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: ColorApplication) {
    @Provides
    @Singleton
    fun provideApplication(): ColorApplication = app
}