package com.jakelaurie.colormemory

import android.app.Application
import com.jakelaurie.colormemory.di.component.DaggerAppComponent
import com.jakelaurie.colormemory.di.module.AppModule

class ColorApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }
}
