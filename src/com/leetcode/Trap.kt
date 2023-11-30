package com.leetcode

import kotlin.math.max

// Trapping Rain Water
object Trap {

    fun trap(height: IntArray): Int {
        var currMax = 0
        var trapped = 0
        var temp = 0
        (height.indices).forEach { i ->
            if (currMax < height[i]) {
                currMax = height[i]
                trapped += temp
                temp = 0
            } else {
                temp += max(0, currMax - height[i])
            }
        }

        currMax = 0
        temp = 0
        (height.size - 1 downTo  0).forEach { i ->
            if (currMax <= height[i]) {
                currMax = height[i]
                trapped += temp
                temp = 0
            } else {
                temp += max(0, currMax - height[i])
            }
        }

        return trapped
    }

}

fun main() {
    println(Trap.trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(Trap.trap(intArrayOf(4,2,0,3,2,5)))
}