package com.jakelaurie.colormemory.ui.highscore

import android.os.Bundle
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.base.BaseActivity

class HighscoreActivity: BaseActivity(), HighscoreContract.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showHighscores()
    }

    private fun showHighscores() {
        supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, HighscoreFragment())
                .commit()
    }
}