package com.jakelaurie.colormemory.ui.game

import android.content.Intent
import android.os.Bundle
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseActivity
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteContract
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteDialogFragment
import com.jakelaurie.colormemory.ui.highscore.HighscoreActivity

class GameActivity : BaseActivity(), GameContract.Callback, GameCompleteContract.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showGameFragment()
    }

    private fun showGameFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, GameFragment())
                .commit()
    }

    override fun onGameComplete(points: Int) {
        GameCompleteDialogFragment.newInstance(points)
                .show(supportFragmentManager, GameCompleteDialogFragment::class.java.simpleName)
    }

    override fun showHighscores() {
        startActivity(Intent(this, HighscoreActivity::class.java))
    }
}
