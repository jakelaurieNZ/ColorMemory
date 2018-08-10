package com.jakelaurie.colormemory.ui.highscore

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseFragment
import com.jakelaurie.colormemory.ui.base.BasePresenter
import com.jakelaurie.colormemory.ui.base.BaseView
import com.jakelaurie.colormemory.ui.base.IBasePresenter
import com.jakelaurie.colormemory.ui.game.GameContract
import com.jakelaurie.colormemory.ui.highscore.list.HighscoreAdapter
import com.jakelaurie.colormemory.ui.widget.RecyclerItemDecoration
import kotlinx.android.synthetic.main.fragment_high_score.*
import javax.inject.Inject

class HighscoreFragment: BaseFragment(), HighscoreContract.View {

    @Inject lateinit var presenter: HighscoreContract.Presenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_high_score, container, false)
        presenter.setView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        highscoreFragmentActionbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        context?.let {
            highScoreRecyclerView.layoutManager = LinearLayoutManager(it)
            highScoreRecyclerView.addItemDecoration(RecyclerItemDecoration(it))
        }
    }

    override fun setAdapter(adapter: HighscoreAdapter) {
        highScoreRecyclerView.adapter = adapter
    }

    override fun getPresenter(): IBasePresenter<out BaseView>? = presenter
}