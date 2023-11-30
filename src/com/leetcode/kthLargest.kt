package com.leetcode

import java.util.PriorityQueue

object kthLargest {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Int>(compareBy { it })
        nums.forEach {
            if (pq.size < k) pq.add(it)
            else {
                if (pq.peek() < it) {
                    pq.remove()
                    pq.add(it)
                }
            }
        }
        return pq.peek()
    }
}

fun main() {
    println(kthLargest.findKthLargest(intArrayOf(6,1,2,3,4,5), 4))
}