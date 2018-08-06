package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.di.module.GameModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GameFragmentProvider {
    @ContributesAndroidInjector(modules = [GameModule::class])
    abstract fun providesGameFragment(): GameFragment
}