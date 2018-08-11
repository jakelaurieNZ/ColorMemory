package com.jakelaurie.colormemory.matchers

import android.support.design.widget.TextInputLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class TextInputLayoutMatcher {
    companion object {
        fun hasErrorText(expectedErrorText: String) = object: TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("Asserting the view contains the text $expectedErrorText")
            }

            override fun matchesSafely(item: View?): Boolean {
                return (item as? TextInputLayout)?.error.toString() == expectedErrorText
            }
        }
    }
}
