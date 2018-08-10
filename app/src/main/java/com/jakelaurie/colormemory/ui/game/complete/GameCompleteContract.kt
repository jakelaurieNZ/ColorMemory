package com.jakelaurie.colormemory.ui.game.complete

import com.jakelaurie.colormemory.ui.base.BaseView
import com.jakelaurie.colormemory.ui.base.IBasePresenter

interface GameCompleteContract {
    interface View: BaseView {
        fun displayPoints(points: Int, isHighScore: Boolean)
        fun onNameError()
        fun onHighscoreAdded()
        fun dismiss()
    }

    interface Presenter: IBasePresenter<View> {
        fun onNameEntered(value: String)
        fun setPoints(points: Int)
    }

    interface Callback {
        fun showHighscores()
        fun dialogDismissed()
    }
}