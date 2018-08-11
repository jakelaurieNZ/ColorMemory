package com.jakelaurie.colormemory

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.jakelaurie.colormemory.ui.game.GameFragment
import com.jakelaurie.colormemory.ui.game.GamePresenter
import com.jakelaurie.colormemory.ui.game.GameViewAdapter
import com.jakelaurie.colormemory.ui.game.ResourceGameDataProvider
import org.junit.Rule
import org.junit.Test
import android.view.ViewGroup

import android.support.test.espresso.matcher.ViewMatchers.*
import com.jakelaurie.colormemory.base.FragmentTestActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Matchers.allOf

class GameFragmentTest {
    val fragment = GameFragment()
    private var gameAdapter: GameViewAdapter? = null

    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() = runOnUiThread {
            activity.startFragment(fragment, this@GameFragmentTest::inject)
        }
    }

    fun inject(fragment: GameFragment) {
        val dataProvider = ResourceGameDataProvider(
                fragment.context!!,
                R.array.card_images_array,
                null) //NO RANDOM
        val adapter = GameViewAdapter()
        gameAdapter = adapter
        fragment.presenter = GamePresenter(adapter, dataProvider, 0L)
    }

    /**
     * Score field should exist and have specific text
     */
    @Test
    fun testDefaultScore() {
        onView(withId(R.id.gameFragmentActionbarCurrentScore))
                .check(matches(isDisplayed()))
                .check(matches(withText("Score: 0")))
    }

    /**
     * Logo should exist
     */
    @Test
    fun testLogo() {
        onView(withId(R.id.gameFragmentActionbarLogo))
                .check(matches(isDisplayed()))
    }

    /**
     * Should be 16 items on the board
     */
    @Test
    fun testGameItemExists() {
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 0), 0), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 0), 1), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 0), 2), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 0), 3), isDisplayed()))

        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 1), 0), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 1), 1), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 1), 2), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 1), 3), isDisplayed()))

        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 2), 0), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 2), 1), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 2), 2), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 2), 3), isDisplayed()))

        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 3), 0), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 3), 1), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 3), 2), isDisplayed()))
        onView(allOf<View>(childAtPosition(childAtPosition(withId(R.id.gameGridLayout), 3), 3), isDisplayed()))
    }

    companion object {
        fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {
            return object: TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {
                    description?.appendText("ChildAt $position")
                    parentMatcher.describeTo(description)
                }

                override fun matchesSafely(view: View?): Boolean {
                    val parent = view?.parent
                    return (parent is ViewGroup && parentMatcher.matches(parent) && view == parent.getChildAt(position))
                }
            }
        }
    }
}

