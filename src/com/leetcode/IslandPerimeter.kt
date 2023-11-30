package com.leetcode

class IslandPerimeter {

    fun getPerimeter(grid: Array<IntArray>, i: Int, j: Int): Int {
        val count = listOf(
            i to j+1,
            i to j-1,
            i+1 to j,
            i-1 to j
        ).count{(i,j) ->
            grid.getOrNull(i)?.getOrNull(j)?.let{ it == 0 } ?: true
        }

        return count
    }

    fun islandPerimeter(grid: Array<IntArray>): Int {
        var perimeter = 0
        grid.indices.forEach{i ->
            grid[0].indices.forEach{j ->
                if (grid[i][j] == 1) {
                    perimeter += getPerimeter(grid, i, j)
                }
            }
        }
        return perimeter
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(0, 1, 0, 0),
        intArrayOf(1, 1, 1, 0),
        intArrayOf(0, 1, 0, 0),
        intArrayOf(1, 1, 0, 0),
    )
    println(IslandPerimeter().islandPerimeter(board))
}