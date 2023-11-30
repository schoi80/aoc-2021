package com.leetcode.array

import kotlin.math.max
import kotlin.math.min

object FindPeakElements {

    fun run(nums: IntArray): Int {

        fun findPeak(start: Int, end: Int) : Int {
            val mid = ((end - start) / 2) + start
            val left = nums.getOrNull(mid - 1)
            val right = nums.getOrNull(mid + 1)
            if ((left == null || left < nums[mid]) && (right == null || nums[mid] > right)) return mid
            return if (left != null && left > nums[mid]) findPeak(start, max(start, mid - 1))
            else findPeak(min(mid + 1, end), end)
        }

        (10 downTo 0)
        return findPeak(0, nums.size - 1)
    }

}

fun main() {
//    println(FindPeakElements.run(intArrayOf(-2147483648)))
    println(FindPeakElements.run(intArrayOf(1,2,3,4,3,2)))
}