package com.leetcode

// Number of Islands
object NumIslands {

    fun numIslands(grid: Array<CharArray>): Int {
        fun markIsland(m: Int, n: Int) {
            if (grid.getOrNull(m)?.getOrNull(n) == '1') {
                grid[m][n] = '2'
                markIsland(m, n - 1)
                markIsland(m, n + 1)
                markIsland(m - 1, n)
                markIsland(m + 1, n)
            }
        }

        var count = 0
        (0 until grid.size).forEach { m ->
            (0 until grid[m].size).forEach { n ->
                if (grid[m][n] == '1') {
                    count++
                    markIsland(m, n)
                }
            }
        }
        return count
    }

}

fun main() {
    println(Trap.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
    println(Trap.trap(intArrayOf(4, 2, 0, 3, 2, 5)))
}