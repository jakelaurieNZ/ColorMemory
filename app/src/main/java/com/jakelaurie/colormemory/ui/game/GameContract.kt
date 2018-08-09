package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.base.BaseView

interface GameContract {
    interface View: BaseView {
        fun setAdapter(gameAdapter: GameViewAdapter)
        fun onScoreChanged(points: Int)
        fun onGameCompleted(points: Int)
    }

    interface Presenter {

    }

    interface Callback {
        fun showHighscores()
    }
}