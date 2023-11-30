package com.fb

fun countSubarrays(arr: IntArray): IntArray {

    val indexMap = arr.mapIndexed { index, i -> i to index }.toMap()
    val result = IntArray(arr.size) { 1 }
    val saSet = mutableSetOf(arr.indices)
    indexMap.keys.sortedDescending().forEach { i ->
        val loc = indexMap[i]

    }

    return intArrayOf()
}

fun main() {
    val arr = intArrayOf(3, 4, 1, 6, 2)
    countSubarrays(arr).forEach {
        println(it)
    }
}
