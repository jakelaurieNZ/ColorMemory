package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.base.BaseView
import com.jakelaurie.colormemory.ui.base.IBasePresenter

interface GameContract {
    interface View: BaseView {
        fun setAdapter(gameAdapter: GameViewAdapter)
        fun onScoreChanged(points: Int)
        fun onGameCompleted(points: Int)
    }

    interface Presenter: IBasePresenter<View> {
        fun newGame()
    }

    interface Callback {
        fun showHighscores()
    }
}