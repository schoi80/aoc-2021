package com.leetcode

fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {

    fun getPath(path: List<Int>, from: Int, to: Int): List<List<Int>> {
        if (from == to) return listOf(path + to)
        val targets = graph[from]
        return targets.filterNot { path.contains(it) }
            .map { getPath(path + from, it, to) }
            .flatten()
    }

    return getPath(listOf(), 0, graph.size - 1)
}

fun main() {

    val graph = arrayOf(
        intArrayOf(4,3,1),
        intArrayOf(3,2,4),
        intArrayOf(3),
        intArrayOf(4),
        intArrayOf()
    )

    val paths = allPathsSourceTarget(graph)
    println(paths)
}
