package com.jakelaurie.colormemory.ui.game.di

import com.jakelaurie.colormemory.di.module.GameCompleteModule
import com.jakelaurie.colormemory.di.module.GameModule
import com.jakelaurie.colormemory.ui.game.GameFragment
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GameFragmentProvider {
    @ContributesAndroidInjector(modules = [GameModule::class])
    abstract fun providesGameFragment(): GameFragment

    @ContributesAndroidInjector(modules = [GameCompleteModule::class])
    abstract fun providesGameCompleteDialogFragment(): GameCompleteDialogFragment
}