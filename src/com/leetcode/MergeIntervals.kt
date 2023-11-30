package com.leetcode

import kotlin.math.max

object MergeIntervals {
    fun merge(a: IntArray, b: IntArray): List<IntArray> {
        return if (a[1] >= b[0]) listOf(intArrayOf(a[0], max(a[1], b[1])))
        else listOf(a, b)
    }

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        result.add(intervals[0])
        (1 until intervals.size).forEach { i ->
            val last = result.removeAt(result.size - 1)
            result.addAll(merge(last, intervals[i]))
        }
        return result.toTypedArray()
    }
}