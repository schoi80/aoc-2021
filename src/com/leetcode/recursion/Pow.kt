package com.leetcode.recursion

import kotlin.math.abs

object Pow {
    val memMap = mutableMapOf<Pair<Double, Int>, Double>()

    fun myPow(x: Double, n: Int): Double {
        if (x == 1.0) return 1.0
        if (n == 0) return 1.0
        if (n == 1) return x
        if (n == -1) return 1/x

        val isOdd = abs(n.rem(2)) == 1
        val partition = n / 2

        val partitionVal = memMap.computeIfAbsent(x to partition) { myPow(x, partition) }
        return partitionVal * partitionVal * (if (isOdd) myPow(x, n/abs(n)) else 1.0)
    }

    fun asd() {
        val a = intArrayOf()
        a.foldIndexed(mutableMapOf<Int, Int>()){i, agg, n ->
            if (n != 0) agg[i] = n
            agg
        }
    }

    class SparseVector(nums: IntArray) {
        val nums = nums.foldIndexed(mutableMapOf<Int, Int>()){i, agg, n ->
            if (n != 0) agg[i] = n
            agg
        }

        // Return the dotProduct of two sparse vectors
        fun dotProduct(vec: SparseVector): Int {
            return nums.keys.intersect(vec.nums.keys).fold(0){agg, key ->
                agg + (nums[key]!! * vec.nums[key]!!)
            }
        }
    }
}