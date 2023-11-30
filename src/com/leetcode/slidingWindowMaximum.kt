package com.leetcode

import java.util.PriorityQueue

// Sliding Window Maximum
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {

    val pq = PriorityQueue<Int>(compareByDescending { it })
    val pq2 = PriorityQueue<Int>(compareByDescending { it })
    val result = IntArray(nums.size)

    repeat(k-1) { pq.add(nums[it]) }

    (k-1 until nums.size).forEach { i ->
        pq.add(nums[i])
        nums.getOrNull(i - k)?.let { pq2.add(it) }
        while (pq.peek() == pq2.peek() && pq.size > k) {
            pq.remove()
            pq2.remove()
        }
        result[i] = pq.peek()
    }

    return result.sliceArray(k-1 until result.size)
}

fun main() {
//    val result = maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 3)
//    val result = maxSlidingWindow(intArrayOf(9,10,9,-7,-4,-8,2,-6), 5)
    val result = maxSlidingWindow(intArrayOf(-6,-10,-7,-1,-9,9,-8,-4,10,-5,2,9,0,-7,7,4,-2,-10,8,7), 7)
    result.onEach { println(it) }
}