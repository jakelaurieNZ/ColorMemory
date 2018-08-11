package com.jakelaurie.colormemory

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.matcher.ViewMatchers.*
import com.jakelaurie.colormemory.base.DBTest
import com.jakelaurie.colormemory.base.FragmentTestActivity
import com.jakelaurie.colormemory.matchers.TextInputLayoutMatcher
import com.jakelaurie.colormemory.ui.game.complete.GameCompleteDialogFragment
import com.jakelaurie.colormemory.ui.game.complete.GameCompletePresenter

class GameCompleteFragmentTest: DBTest() {
    val fragment = GameCompleteDialogFragment()

    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() = runOnUiThread {
            activity.startFragment(fragment, this@GameCompleteFragmentTest::inject)
        }
    }

    fun inject(fragment: GameCompleteDialogFragment) {
        initDb()
        val scoreDAO = db.scoreDao() //from parent class
        fragment.presenter = GameCompletePresenter(scoreDAO)
    }

    /**
     * Test all view elements are onscreen
     */
    @Test
    fun testDisplay() {
        onView(withId(R.id.gameCompleteTitle))
                .check(matches(isDisplayed()))

        onView(withId(R.id.gameCompleteYourScoreTitle))
                .check(matches(isDisplayed()))

        onView(withId(R.id.gameCompleteYourScore))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.gameCompleteCloseButton))
                .check(matches(isDisplayed()))

        onView(withId(R.id.gameCompleteContinueButton))
                .check(matches(isDisplayed()))

        onView(withId(R.id.gameCompleteEnterScoreLayout))
                .check(matches(isDisplayed()))

        onView(withId(R.id.gameCompleteEnterScoreInput))
                .check(matches(isDisplayed()))
    }

    /**
     * Test click button shows invalid name error
     */
    @Test
    fun testError() {
        onView(withId(R.id.gameCompleteContinueButton))
                .perform(click())
        onView(withId(R.id.gameCompleteEnterScoreLayout))
                .check(matches(TextInputLayoutMatcher.hasErrorText("Please enter a valid name")))
    }
}

