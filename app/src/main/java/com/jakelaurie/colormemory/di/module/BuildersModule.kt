package com.jakelaurie.colormemory.di.module

import com.jakelaurie.colormemory.di.scope.ActivityScope
import com.jakelaurie.colormemory.ui.game.GameActivity
import com.jakelaurie.colormemory.ui.game.di.GameFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, GameFragmentProvider::class])
    abstract fun contributeMainActivity(): GameActivity
}