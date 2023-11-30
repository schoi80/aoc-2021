package com.fb

import kotlin.math.min

fun matchingPairs(a: String, b: String): Int {
    var matchCount = 0
    val matchCharCount = mutableMapOf<Char, Int>().withDefault { 0 }
    val am = mutableSetOf<Char>()
    val bm = mutableSetOf<Char>()
    a.zip(b).forEach { (a, b) ->
        if (a == b) {
            matchCharCount[a] = matchCharCount.getValue(a) + 1
            matchCount++
        } else {
            am += a
            bm += b
        }
    }

    // if mismatch is not found, then check matchCharCount.
    // if there is at least 1 matching char that repeats more than once,
    // we don't have to lose any match count.
    // otherwise, we lose 2.
    if (am.isEmpty()) {
        return if (matchCharCount.count { it.value > 1 } > 0)
            matchCount
        else matchCount - 2
    }

    val intersect = am.intersect(bm)
    return matchCount + min(intersect.size, 2)
}

fun main() {
    println(matchingPairs("abcde", "abeeg"))
}
