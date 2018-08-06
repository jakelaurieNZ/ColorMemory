package com.jakelaurie.colormemory.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.BaseFragment
import com.jakelaurie.colormemory.ui.BasePresenter
import com.jakelaurie.colormemory.ui.BaseView
import javax.inject.Inject

class GameFragment: BaseFragment(), GameContract.View {

    @Inject lateinit var presenter: GamePresenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.setView(this)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun getPresenter(): BasePresenter<out BaseView>? = presenter
}
