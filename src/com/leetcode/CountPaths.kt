package com.leetcode

import java.util.PriorityQueue
import kotlin.math.pow

fun countPaths(n: Int, roads: Array<IntArray>): Int {

    val graph = roads.flatMap {
        listOf(
            intArrayOf(it[0], it[1], it[2]),
            intArrayOf(it[1], it[0], it[2])
        )
    }.groupBy { it[0] }

    fun getNextNodes(from: Int): List<Pair<Int, Long>> = graph[from]!!.map { it[1] to it[2].toLong() }

    val visitedCost = LongArray(n) { Long.MAX_VALUE }.apply { this[0] = 0 }
    val pathCount = LongArray(n) { 0 }.apply { this[0] = 1 }

    val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second }).apply { add(0 to 0) }

    while (queue.isNotEmpty()) {
        val (from, cost) = queue.remove()
        //if (fromNode == n - 1) break
        getNextNodes(from)
            .map { it.first to (cost + it.second) }
            .forEach { (to, cost) ->
                if (visitedCost[to] > cost) {
                    pathCount[to] = pathCount[from]
                    visitedCost[to] = cost
                    queue.add(to to cost)
                } else if (visitedCost[to] == cost) {
                    pathCount[to] += pathCount[from]
                }
            }
    }

    return pathCount[n - 1].rem(10.0.pow(9) + 7).toInt()
}

fun main() {

//    val graph = arrayOf(
//        intArrayOf(0, 1, 3972),
//        intArrayOf(2, 1, 1762),
//        intArrayOf(3, 1, 4374),
//        intArrayOf(0, 3, 8346),
//        intArrayOf(3, 2, 2612),
//        intArrayOf(4, 0, 6786),
//        intArrayOf(5, 4, 1420),
//        intArrayOf(2, 6, 7459),
//        intArrayOf(1, 6, 9221),
//        intArrayOf(6, 3, 4847),
//        intArrayOf(5, 6, 4987),
//        intArrayOf(7, 0, 14609),
//        intArrayOf(7, 1, 10637),
//        intArrayOf(2, 7, 8875),
//        intArrayOf(7, 6, 1416),
//        intArrayOf(7, 5, 6403),
//        intArrayOf(7, 3, 6263),
//        intArrayOf(4, 7, 7823),
//        intArrayOf(5, 8, 10184),
//        intArrayOf(8, 1, 14418),
//        intArrayOf(8, 4, 11604),
//        intArrayOf(7, 8, 3781),
//        intArrayOf(8, 2, 12656),
//        intArrayOf(8, 0, 18390),
//        intArrayOf(5, 9, 15094),
//        intArrayOf(7, 9, 8691),
//        intArrayOf(9, 6, 10107),
//        intArrayOf(9, 1, 19328),
//        intArrayOf(9, 4, 16514),
//        intArrayOf(9, 2, 17566),
//        intArrayOf(9, 0, 23300),
//        intArrayOf(8, 9, 4910),
//        intArrayOf(9, 3, 14954),
//        intArrayOf(4, 10, 26060),
//        intArrayOf(2, 10, 27112),
//        intArrayOf(10, 1, 28874),
//        intArrayOf(8, 10, 14456),
//        intArrayOf(3, 10, 24500),
//        intArrayOf(5, 10, 24640),
//        intArrayOf(10, 6, 19653),
//        intArrayOf(10, 0, 32846),
//        intArrayOf(10, 9, 9546),
//        intArrayOf(10, 7, 18237),
//        intArrayOf(11, 7, 21726),
//        intArrayOf(11, 2, 30601),
//        intArrayOf(4, 11, 29549),
//        intArrayOf(11, 0, 36335),
//        intArrayOf(10, 11, 3489),
//        intArrayOf(6, 11, 23142),
//        intArrayOf(3, 11, 27989),
//        intArrayOf(11, 1, 32363),
//        intArrayOf(11, 8, 17945),
//        intArrayOf(9, 11, 13035),
//        intArrayOf(5, 11, 28129),
//        intArrayOf(2, 12, 33902),
//        intArrayOf(5, 12, 31430),
//        intArrayOf(6, 12, 26443),
//        intArrayOf(4, 12, 32850),
//        intArrayOf(12, 3, 31290),
//        intArrayOf(11, 12, 3301),
//        intArrayOf(12, 1, 35664),
//        intArrayOf(7, 13, 28087),
//        intArrayOf(13, 8, 24306),
//        intArrayOf(6, 13, 29503),
//        intArrayOf(11, 13, 6361),
//        intArrayOf(4, 13, 35910),
//        intArrayOf(13, 12, 3060),
//        intArrayOf(3, 13, 34350),
//        intArrayOf(13, 5, 34490),
//        intArrayOf(13, 2, 36962),
//        intArrayOf(10, 13, 9850),
//        intArrayOf(9, 13, 19396),
//        intArrayOf(12, 14, 8882),
//        intArrayOf(8, 14, 30128),
//        intArrayOf(14, 6, 35325),
//        intArrayOf(14, 5, 40312),
//        intArrayOf(1, 14, 44546),
//        intArrayOf(11, 14, 12183),
//        intArrayOf(15, 12, 13581),
//        intArrayOf(2, 15, 47483),
//        intArrayOf(4, 15, 46431),
//        intArrayOf(15, 10, 20371),
//        intArrayOf(15, 14, 4699),
//        intArrayOf(15, 6, 40024),
//        intArrayOf(15, 7, 38608),
//        intArrayOf(1, 15, 49245),
//        intArrayOf(11, 15, 16882),
//        intArrayOf(8, 15, 34827),
//        intArrayOf(0, 15, 53217),
//        intArrayOf(5, 15, 45011),
//        intArrayOf(15, 3, 44871),
//        intArrayOf(16, 2, 53419),
//        intArrayOf(16, 9, 35853),
//        intArrayOf(1, 16, 55181),
//        intArrayOf(16, 7, 44544),
//        intArrayOf(8, 16, 40763),
//        intArrayOf(0, 16, 59153),
//        intArrayOf(15, 16, 5936),
//        intArrayOf(16, 10, 26307),
//        intArrayOf(16, 6, 45960),
//        intArrayOf(12, 16, 19517),
//        intArrayOf(17, 2, 57606),
//        intArrayOf(17, 3, 54994),
//        intArrayOf(17, 14, 14822),
//        intArrayOf(17, 11, 27005),
//        intArrayOf(0, 17, 63340),
//        intArrayOf(17, 7, 48731),
//        intArrayOf(8, 17, 44950),
//        intArrayOf(17, 16, 4187),
//        intArrayOf(5, 17, 55134),
//        intArrayOf(17, 10, 30494),
//        intArrayOf(17, 9, 40040),
//        intArrayOf(17, 12, 23704),
//        intArrayOf(13, 17, 20644),
//        intArrayOf(17, 1, 59368),
//    )
//
//    println(countPaths(18, graph))
    println(Int.MAX_VALUE)
    val graph = arrayOf(
        intArrayOf(0, 6, 7),
        intArrayOf(0, 1, 2),
        intArrayOf(1, 2, 3),
        intArrayOf(1, 3, 3),
        intArrayOf(6, 3, 3),
        intArrayOf(3, 5, 1),
        intArrayOf(6, 5, 1),
        intArrayOf(2, 5, 1),
        intArrayOf(0, 4, 5),
        intArrayOf(4, 6, 2),
    )
    println(countPaths(7, graph))
}
