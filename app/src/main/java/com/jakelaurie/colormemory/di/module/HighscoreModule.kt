package com.jakelaurie.colormemory.di.module

import com.jakelaurie.colormemory.ui.highscore.HighscoreContract
import com.jakelaurie.colormemory.ui.highscore.HighscorePresenter
import dagger.Module
import dagger.Provides

@Module
class HighscoreModule {
    @Provides
    fun providesPresenter(presenter: HighscorePresenter): HighscoreContract.Presenter = presenter
}