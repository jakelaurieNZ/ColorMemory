package com.jakelaurie.colormemory

import com.jakelaurie.colormemory.data.database.ScoreDAO
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteContract
import com.jakelaurie.colormemory.ui.game.complete.GameCompletePresenter
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

class GameCompletePresenterTest {
    @Rule
    @JvmField var testSchedulerRule = TestSchedulerRule() //RxScheduler

    @Mock
    lateinit var scoreDao: ScoreDAO

    private lateinit var presenter: GameCompletePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = GameCompletePresenter(scoreDao)
    }


    /**
     * Should NOT be a high score if is lower than lowest value and we have 10 in table
     */
    @Test
    fun testHighScoreValues() {
        testScores(0,10, Int.MAX_VALUE, false)
    }

    /**
     * Should be a high score if 'currentScore' is higher than than highest value
     * Even if we have 10 in table
     */
    @Test
    fun testLowScoreValues() {
        testScores(0,10, Int.MIN_VALUE, true)
    }

    /**
     * Should always be a high score if we have less than 10 highscores
     * Regardless of 'currentScore'
     */
    @Test
    fun testFewScores() {
        testScores(0,1, Int.MAX_VALUE, true)
        testScores(0,1, Int.MIN_VALUE, true)
    }

    /**
     * Generic score test with conditions
     */
    private fun testScores(currentScore: Int,
                   createCount: Int,
                   createValue: Int,
                   expectIsHighScore: Boolean) {
        whenever(scoreDao.queryScores())
                .thenReturn(Single.just(ScoresTestUtil.createScores(createCount, createValue))
                        .toFlowable())
        presenter.mPoints = currentScore

        var wasHighScore = false
        var didSetHighScore = false

        presenter.setView(object: GameCompleteContract.View {
            override fun displayPoints(points: Int, isHighScore: Boolean) {
                didSetHighScore = true
                wasHighScore = isHighScore
            }

            override fun onNameError() {

            }

            override fun onHighscoreAdded() {

            }

            override fun dismiss() {

            }
        })
        presenter.resume()

        Assert.assertTrue(didSetHighScore)
        Assert.assertEquals(expectIsHighScore, wasHighScore)
    }

    @After
    fun cleanupPresenter() {
        presenter.mPoints = 0
    }

    @Test
    fun testValidNameEntry() {
        testNameEntry("Test Testerson", false, true)
    }

    @Test
    fun testInvalidNameEntry() {
        testNameEntry("", true, false)
    }

    fun testNameEntry(nameInput: String, expectNameError: Boolean, expectHighscoreAdded: Boolean) {
        var onNameError = false
        var onHighscoreAdded = false

        presenter.setView(object: GameCompleteContract.View {
            override fun displayPoints(points: Int, isHighScore: Boolean) {

            }

            override fun onNameError() {
                onNameError = true
            }

            override fun onHighscoreAdded() {
                onHighscoreAdded = true
            }

            override fun dismiss() {

            }
        })

        presenter.onNameEntered(nameInput)
        Assert.assertEquals(expectNameError, onNameError)
        Assert.assertEquals(expectHighscoreAdded, onHighscoreAdded)
    }
}