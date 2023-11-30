package com.leetcode.array

object OceanView {
    fun run(heights: IntArray): IntArray {
        var currMax = 0
        val oceanViewList = mutableListOf<Int>()
        (heights.size - 1 downTo 0).forEach {i ->
            if (heights[i] > currMax) {
                currMax = heights[i]
                oceanViewList.add(0, i)
            }
        }
        return oceanViewList.toIntArray()
    }
}

fun main() {
    val input = intArrayOf(4, 3, 2, 1)
    val result = OceanView.run(input)
    result.onEach { println(it) }
}