package com.leetcode.array

object SubarraySum {
    fun run(nums: IntArray, k: Int): Int {
        var res = 0
        var total = 0
        val hm = mutableMapOf<Int, Int>()
        hm[0] = 1
        for (n in nums) {
            total += n
            res += hm.getOrDefault(total - k, 0)
            hm[total] = hm.getOrDefault(total, 0) + 1
        }
        return res
    }
}