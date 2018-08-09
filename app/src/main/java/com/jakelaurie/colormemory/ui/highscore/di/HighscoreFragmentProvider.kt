package com.jakelaurie.colormemory.ui.highscore.di

import com.jakelaurie.colormemory.di.module.HighscoreModule
import com.jakelaurie.colormemory.ui.highscore.HighscoreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HighscoreFragmentProvider {
    @ContributesAndroidInjector(modules = [HighscoreModule::class])
    abstract fun providesGameFragment(): HighscoreFragment
}