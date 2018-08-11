package com.jakelaurie.colormemory

import com.jakelaurie.colormemory.data.database.ScoreDAO
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteContract
import com.jakelaurie.colormemory.ui.game.complete.GameCompletePresenter
import com.jakelaurie.colormemory.ui.highscore.HighscoreContract
import com.jakelaurie.colormemory.ui.highscore.HighscorePresenter
import com.jakelaurie.colormemory.ui.highscore.list.HighscoreAdapter
import com.jakelaurie.colormemory.util.ScoresTestUtil
import com.jakelaurie.colormemory.util.TestSchedulerRule
import io.reactivex.Single
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class HighscorePresenterTest {
    @Rule
    @JvmField
    var testSchedulerRule = TestSchedulerRule() //RxScheduler

    @Mock
    lateinit var scoreDao: ScoreDAO

    @Mock
    lateinit var highscoreAdapter: HighscoreAdapter

    private lateinit var presenter: HighscorePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = HighscorePresenter(highscoreAdapter, scoreDao)
    }

    @Test
    fun testAdapterSet() {
        var adapterWasSet = false
        presenter.setView(object: HighscoreContract.View {
            override fun setAdapter(adapter: HighscoreAdapter) {
                adapterWasSet = true
            }
        })

        presenter.resume()

        Assert.assertTrue(adapterWasSet)
    }

}