package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.ui.BasePresenter
import com.jakelaurie.colormemory.ui.BaseView
import javax.inject.Inject

class GamePresenter @Inject constructor(): BasePresenter<GameContract.View>(),
        GameContract.Presenter {

}