package com.jakelaurie.colormemory

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.jakelaurie.colormemory.base.DBTest
import com.jakelaurie.colormemory.base.FragmentTestActivity
import com.jakelaurie.colormemory.model.Score
import com.jakelaurie.colormemory.ui.highscore.HighscoreFragment
import com.jakelaurie.colormemory.ui.highscore.HighscorePresenter
import com.jakelaurie.colormemory.ui.highscore.list.HighscoreAdapter
import org.junit.Rule
import org.junit.Test

class HighscoreFragmentTest: DBTest() {
    val fragment = HighscoreFragment()

    private val playerName = "test"
    private val playerScore = 100

    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() = runOnUiThread {
            activity.startFragment(fragment, this@HighscoreFragmentTest::inject)
        }
    }

    fun inject(fragment: HighscoreFragment) {
        initDb()
        val scoreDAO = db.scoreDao()

        val score = Score("$playerScore")
        score.score = playerScore
        score.playerName = playerName

        scoreDAO.addScore(score)

        fragment.presenter = HighscorePresenter(HighscoreAdapter(), scoreDAO)
    }

    /**
     * Test actionbar
     */
    @Test
    fun testActionbar() {
        onView(ViewMatchers.withId(R.id.highscoreActionbarTitle))
                .check(matches(ViewMatchers.isDisplayed()))
                .check(matches(ViewMatchers.withText("Top 10 high scores")))
    }

    /**
     * Test header and recycler visibility
     */
    @Test
    fun testHeader() {
        onView(ViewMatchers.withId(R.id.titleHighScoreRank))
                .check(matches(ViewMatchers.isDisplayed()))
                .check(matches(ViewMatchers.withText("Rank")))

        onView(ViewMatchers.withId(R.id.titleHighScoreName))
                .check(matches(ViewMatchers.isDisplayed()))
                .check(matches(ViewMatchers.withText("Name")))

        onView(ViewMatchers.withId(R.id.titleHighScoreScore))
                .check(matches(ViewMatchers.isDisplayed()))
                .check(matches(ViewMatchers.withText("Points")))
    }

    /**
     * Test the 'score' we put in the Db is actually shown in recycler
     */
    @Test
    fun testRecyclerContent() {
        onView(ViewMatchers.withId(R.id.highScoreRecyclerView))
                .check(matches(ViewMatchers.isDisplayed()))

        onView(withText("#1")).check(matches(isDisplayed()))
        onView(withText(playerName)).check(matches(isDisplayed()))
        onView(withText("$playerScore")).check(matches(isDisplayed()))
    }
}