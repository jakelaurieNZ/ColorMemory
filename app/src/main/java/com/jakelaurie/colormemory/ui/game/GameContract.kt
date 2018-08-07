package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.BaseView

interface GameContract {
    interface View: BaseView {
        fun setAdapter(gameAdapter: GameViewAdapter)
        fun onGameCompleted(points: Int)
    }

    interface Presenter {

    }

    interface Callback {

    }
}