package com.jakelaurie.colormemory.ui.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseFragment
import com.jakelaurie.colormemory.ui.base.BasePresenter
import com.jakelaurie.colormemory.ui.base.BaseView
import kotlinx.android.synthetic.main.fragment_game.*
import javax.inject.Inject

class GameFragment: BaseFragment(), GameContract.View {

    @Inject lateinit var presenter: GamePresenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        presenter.setView(this)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getCallback()?.onGameComplete(69)
    }

    override fun setAdapter(gameAdapter: GameViewAdapter) {
        gameGridLayout.adapter = gameAdapter
    }

    override fun onGameCompleted(points: Int) {
        getCallback()?.onGameComplete(points)
    }

    private fun getCallback(): GameContract.Callback? {
        return context as? GameContract.Callback
    }

    override fun getPresenter(): BasePresenter<out BaseView>? = presenter
}
