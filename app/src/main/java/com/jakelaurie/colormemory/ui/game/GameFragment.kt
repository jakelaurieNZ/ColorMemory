package com.jakelaurie.colormemory.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.BaseFragment
import com.jakelaurie.colormemory.ui.BasePresenter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameFragment: DaggerFragment(), GameContract.View {

    @Inject lateinit var presenter: GamePresenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

//    override fun getPresenter(): BasePresenter? {
//        return presenter
//    }
}