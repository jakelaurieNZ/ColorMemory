package com.jakelaurie.colormemory.di.module

import com.jakelaurie.colormemory.ui.MainActivity
import com.jakelaurie.colormemory.ui.game.GameFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [GameFragmentProvider::class])
    abstract fun contributeMainActivity(): MainActivity
}