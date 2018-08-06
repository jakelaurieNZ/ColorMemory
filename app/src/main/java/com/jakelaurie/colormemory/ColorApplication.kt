package com.jakelaurie.colormemory

import com.jakelaurie.colormemory.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ColorApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out ColorApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}

