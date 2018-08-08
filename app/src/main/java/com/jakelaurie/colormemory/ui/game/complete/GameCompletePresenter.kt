package com.jakelaurie.colormemory.ui.game.complete

import com.jakelaurie.colormemory.ui.base.BasePresenter
import javax.inject.Inject

class GameCompletePresenter @Inject constructor():
        BasePresenter<GameCompleteContract.View>(), GameCompleteContract.Presenter {

    var points: Int = 0

    override fun resume() {
        super.resume()
        getView()?.displayPoints(points, isHighScore(points))
    }

    private fun isHighScore(points: Int): Boolean {
        return true
    }

    override fun onNameEntered(value: String) {
        if(value.isNotEmpty()) {

        } else {
            getView()?.onNameError()
        }
    }
}