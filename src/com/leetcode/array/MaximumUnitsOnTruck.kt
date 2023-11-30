package com.leetcode.array

import kotlin.math.min

object MaximumUnitsOnTruck {

    fun maximumUnits(boxTypes: Array<IntArray>, truckSize: Int): Int {
        var remaining = truckSize
        var totalUnits = 0
        boxTypes.sortedByDescending { it[1] }.forEach {
            val boxCount = it[0]
            val numOfUnits = it[1]
            val allowedCount = min(boxCount, remaining)
            remaining -= allowedCount
            totalUnits += numOfUnits * allowedCount
            if (remaining == 0)
                return totalUnits
        }
        return totalUnits
    }
}

fun main() {

}