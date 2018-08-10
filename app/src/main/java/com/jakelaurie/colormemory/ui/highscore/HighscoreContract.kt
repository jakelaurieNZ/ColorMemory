package com.jakelaurie.colormemory.ui.highscore

import com.jakelaurie.colormemory.ui.base.BaseView
import com.jakelaurie.colormemory.ui.base.IBasePresenter
import com.jakelaurie.colormemory.ui.highscore.list.HighscoreAdapter

class HighscoreContract {
    interface View: BaseView {
        fun setAdapter(adapter: HighscoreAdapter)
    }

    interface Presenter: IBasePresenter<View> {

    }

    interface Callback {

    }
}