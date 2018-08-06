package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.BasePresenter
import javax.inject.Inject

class GamePresenter @Inject constructor(val adapter: GameViewAdapter): BasePresenter<GameContract.View>(),
        GameContract.Presenter {

    private val currentSelection: Selection? = null

    override fun resume() {
        super.resume()
        getView()?.setAdapter(adapter)
    }

    override fun viewSelected(id: Int, position: Int) {
        adapter.toggleItemSelected(id, position)
    }
}

data class Selection(val id: Int, val position: Int)
