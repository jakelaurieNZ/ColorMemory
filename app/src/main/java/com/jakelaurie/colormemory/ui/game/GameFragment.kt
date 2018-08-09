package com.jakelaurie.colormemory.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseFragment
import com.jakelaurie.colormemory.ui.base.BasePresenter
import com.jakelaurie.colormemory.ui.base.BaseView
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteContract
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteDialogFragment
import kotlinx.android.synthetic.main.fragment_game.*
import javax.inject.Inject

class GameFragment: BaseFragment(), GameContract.View, GameCompleteContract.Callback  {

    @Inject lateinit var presenter: GamePresenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        gameFragmentActionbarHighscoreButton.setOnClickListener {
            getCallback()?.showHighscores()
        }
    }

    override fun setAdapter(gameAdapter: GameViewAdapter) {
        gameGridLayout.adapter = gameAdapter
    }

    override fun onGameCompleted(points: Int) {
        val dialog = GameCompleteDialogFragment.newInstance(points)
        dialog.setTargetFragment(this, this.hashCode())
        dialog.show(fragmentManager, GameCompleteDialogFragment::class.java.simpleName)
    }

    override fun onScoreChanged(points: Int) {
        gameFragmentActionbarCurrentScore.text = getString(R.string.score_format, points)
    }

    private fun getCallback(): GameContract.Callback? {
        return context as? GameContract.Callback
    }

    override fun showHighscores() {
        getCallback()?.showHighscores()
    }

    override fun dialogDismissed() {
        presenter.newGame()
    }

    override fun getPresenter(): BasePresenter<out BaseView>? = presenter
}
