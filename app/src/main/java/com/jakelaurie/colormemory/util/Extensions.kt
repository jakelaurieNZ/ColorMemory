package com.jakelaurie.colormemory.util

fun Int.closestDivisible(denominator: Int): Int {
    val c1 = this - this % denominator
    val c2 = this + denominator - this % denominator
    return if (this - c1 > c2 - this) {
        c2
    } else {
        c1
    }
}