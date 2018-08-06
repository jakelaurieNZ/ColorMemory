package com.jakelaurie.colormemory.di.module

import android.content.Context
import com.jakelaurie.colormemory.ColorApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAppContext(application: ColorApplication): Context = application
}