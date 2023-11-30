package com.leetcode

import kotlin.random.Random

class RandomPickWithWeight(w: IntArray) {
    private val wArr: List<Double>

    init {
        val sum = w.sum()
        wArr = w.map { it.toDouble() / sum.toDouble() }
    }

    fun pickIndex() : Int {
        val r = Random.nextDouble(0.0, 1.0)
        var sum = 0.0
        wArr.forEachIndexed{i, w ->
            sum += w
            if (sum >= r) return i
        }
        return wArr.size - 1
    }

}

fun main() {
    val r = RandomPickWithWeight(intArrayOf(1,3))
    println(r.pickIndex())
    println(r.pickIndex())
    println(r.pickIndex())
    println(r.pickIndex())
    println(r.pickIndex())
    println(r.pickIndex())
    println(r.pickIndex())
}