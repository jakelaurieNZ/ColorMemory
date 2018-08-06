package com.jakelaurie.colormemory.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.View
import java.util.*

fun Int.closestDivisible(denominator: Int): Int {
    val c1 = this - this % denominator
    val c2 = this + denominator - this % denominator
    return if (this - c1 > c2 - this) {
        c2
    } else {
        c1
    }
}

fun ObjectAnimator.addEndListener(end: (() -> Unit)) {
    this.addListener(object: Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}

        override fun onAnimationEnd(animation: Animator?) {
            end()
        }

        override fun onAnimationCancel(animation: Animator?) {
            end()
        }

        override fun onAnimationStart(animation: Animator?) {}
    })
}

fun View.setRandomBackground() {
    val rnd = Random()
    val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    setBackgroundColor(color)
}
