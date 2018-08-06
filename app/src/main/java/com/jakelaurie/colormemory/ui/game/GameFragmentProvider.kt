package com.jakelaurie.colormemory.ui.game

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GameFragmentProvider {
    @ContributesAndroidInjector()
    abstract fun providesGameFragment(): GameFragment
}