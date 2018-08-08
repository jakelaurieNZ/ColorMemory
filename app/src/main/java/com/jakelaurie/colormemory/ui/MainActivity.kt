package com.jakelaurie.colormemory.ui

import android.os.Bundle
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseActivity
import com.jakelaurie.colormemory.ui.game.GameContract
import com.jakelaurie.colormemory.ui.game.GameFragment
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteDialogFragment

class MainActivity : BaseActivity(), GameContract.Callback {
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
}
