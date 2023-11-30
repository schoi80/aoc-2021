package com.fb

fun pairSums(a: IntArray, k: Int): Int {
    val m = a.groupBy { it }.mapValues { it.value.size }
    val arr = m.keys.sorted()
    var i = 0
    var j = arr.size - 1
    var count = 0
    while (i <= j) {
        val sum = arr[i] + arr[j]
        when {
            sum > k -> j--
            sum < k -> i++
            else -> {
                if (i != j)
                    count += m[arr[i]]!! * m[arr[j]]!!
                else if (m[arr[i]]!! > 1)
                    count += (m[arr[i]]!! * (m[arr[i]]!! - 1)) / 2
                i++
                j--
            }
        }
    }

    return count
}

fun main() {
    val arr = intArrayOf(1, 5, 3, 3, 3)
    println(pairSums(arr, 10))
}
