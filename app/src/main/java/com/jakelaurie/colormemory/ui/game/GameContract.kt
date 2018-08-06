package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.BaseView

interface GameContract {
    interface View: BaseView {
        fun setAdapter(gameAdapter: GameViewAdapter)
    }

    interface Presenter {
        fun viewSelected(id: Int, position: Int)
    }

    interface Callback {

    }
}