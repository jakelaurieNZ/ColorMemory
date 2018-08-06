package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.BasePresenter
import javax.inject.Inject

class GamePresenter @Inject constructor(val adapter: GameViewAdapter): BasePresenter<GameContract.View>(),
        GameContract.Presenter {

    override fun resume() {
        super.resume()
        getView()?.setAdapter(adapter)
    }
}