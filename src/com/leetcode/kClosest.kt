package com.leetcode

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

// K closest to origin (n log k)
fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {

    fun IntArray.distance(): Double {
        return sqrt(this[0].toDouble().pow(2) + this[1].toDouble().pow(2))
    }

    val pq = PriorityQueue<IntArray>(compareByDescending { it.distance() })

    points.forEach { p ->
        if (pq.size < k) pq.add(p)
        else {
            if (pq.peek()?.let { it.distance() > p.distance() } == true) {
                pq.remove()
                pq.add(p)
            }
        }
    }

    return pq.toList().toTypedArray()
}

fun main() {
    val count = kClosest(
        arrayOf(
            intArrayOf(3, 3),
            intArrayOf(5, -1),
            intArrayOf(-2, 4),
        ), 2
    )
    count.onEach { println("(${it[0]}, ${it[1]})") }
}
