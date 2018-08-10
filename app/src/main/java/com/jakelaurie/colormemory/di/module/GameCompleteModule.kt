package com.jakelaurie.colormemory.di.module

import com.jakelaurie.colormemory.ui.game.complete.GameCompleteContract
import com.jakelaurie.colormemory.ui.game.complete.GameCompletePresenter
import dagger.Module
import dagger.Provides

@Module
class GameCompleteModule {
    @Provides
    fun providesPresenter(presenter: GameCompletePresenter): GameCompleteContract.Presenter = presenter
}