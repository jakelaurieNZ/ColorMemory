package com.jakelaurie.colormemory.ui.game.complete

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseView
import com.jakelaurie.colormemory.ui.base.BaseDialogFragment
import com.jakelaurie.colormemory.ui.base.BasePresenter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_game_complete_dialog.*
import java.util.*
import javax.inject.Inject

class GameCompleteDialogFragment: BaseDialogFragment(), GameCompleteContract.View {
    @Inject lateinit var presenter: GameCompletePresenter

    private var textChangedDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(
                R.layout.fragment_game_complete_dialog,
                container,
                false)

        presenter.setView(this)
        presenter.points = arguments?.getInt(kPoints)?: 0
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textChangedDisposable = RxTextView.textChanges(gameCompleteEnterScoreInput).subscribe {
            gameCompleteEnterScoreLayout.error = null
        }

        gameCompleteCloseButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        //Dispose our observables
        textChangedDisposable?.let {
            if(!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun displayPoints(points: Int, isHighScore: Boolean) {
        gameCompleteYourScore.text = String.format(Locale.getDefault(), "%d", points)
        if(isHighScore) {
            gameCompleteTitle.setText(R.string.game_complete_title_high_score)
            gameCompleteContinueButton.setText(R.string.game_complete_highscore_entered_button_title)
            gameCompleteContinueButton.setOnClickListener {
                presenter.onNameEntered(gameCompleteEnterScoreInput.text.toString())
            }
            gameCompleteEnterScoreLayout.visibility = View.VISIBLE
        } else {
            gameCompleteTitle.setText(R.string.game_complete_title_standard)
            gameCompleteContinueButton.setText(R.string.game_complete_highscore_button_title)
            gameCompleteContinueButton.setOnClickListener {
                dismiss()
                getCallback()?.showHighscores()
            }
            gameCompleteEnterScoreLayout.visibility = View.GONE
        }
    }

    override fun onNameError() {
        gameCompleteEnterScoreLayout.error =
                context?.getString(R.string.game_complete_enter_name_error)
    }

    override fun onHighscoreAdded() {
        dismiss()
        getCallback()?.showHighscores()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        getCallback()?.dialogDismissed()
    }

    private fun getCallback(): GameCompleteContract.Callback? {
        return targetFragment as? GameCompleteContract.Callback
    }

    override fun getPresenter(): BasePresenter<out BaseView>? = presenter

    companion object {
        const val kPoints = "kPoints"

        fun newInstance(points: Int): GameCompleteDialogFragment {
            val bundle = Bundle()
            bundle.putInt(kPoints, points)
            val fragment = GameCompleteDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}