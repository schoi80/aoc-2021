package com.leetcode

// Median of Two Sorted Arrays
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val length = nums1.size + nums2.size
    var mid = length / 2
    val isEven = length.rem(2) == 0

    var i = 0
    var j = 0
    var prev = 0
    var curr = 0

    fun setCurr(x: Int) {
        prev = curr
        curr = x
    }

    while (mid >= 0) {
        if (i == nums1.size) {
            setCurr(nums2[j++])
        } else if (j == nums2.size) {
            setCurr(nums1[i++])
        } else {
            if (nums1[i] < nums2[j]) {
                setCurr(nums1[i++])
            } else {
                setCurr(nums2[j++])
            }
        }
        mid--
    }

    return if (!isEven) {
        curr.toDouble()
    } else {
        (prev + curr) / 2.0
    }
}

fun main() {
    println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4, 5)))
}
