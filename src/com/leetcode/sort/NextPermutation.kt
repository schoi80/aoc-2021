package com.leetcode.sort

// quicksort
object NextPermutation {

    fun run(nums: IntArray) {

        if (nums.size <= 1) return

        fun swap(i: Int, j: Int) {
            val t = nums[i]
            nums[i] = nums[j]
            nums[j] = t
        }

        fun partition(low: Int, high: Int): Int {
            var i = low - 1
            (low until high).forEach { j ->
                if (nums[j] < nums[high]) {
                    i++
                    swap(i, j)
                }
            }
            swap(i + 1, high)
            return i + 1
        }

        fun quickSort(i: Int, j: Int) {
            if (i < j) {
                val pi = partition(i, j)
                quickSort(i, pi - 1)
                quickSort(pi + 1, j)
            }
        }

        val end = nums.size - 1
        var i = end
        while (i > 0) {
            if (nums[i - 1] < nums[i]) {
                val t = i - 1
                i = end
                while (i >= t) {
                    if (nums[i] > nums[t]) {
                        swap(i, t)
                        quickSort(t + 1, end)
                        return
                    } else i--
                }
            } else i--
        }

        quickSort(0, end)
    }
}

fun main() {
    val input = intArrayOf(2,1,3,4,5)
    NextPermutation.run(input)
    input.onEach { println(it) }
}
