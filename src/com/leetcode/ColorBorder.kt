package com.leetcode

import kotlin.math.abs

class ColorBorder {

    fun colorBorder(grid: Array<IntArray>, row: Int, col: Int, color: Int): Array<IntArray> {
        val origColor = grid[row][col]

        fun fillColor(i: Int, j: Int) {
            if (grid.getOrNull(i)?.getOrNull(j) != origColor) return
            grid[i][j] = origColor * -1
            listOf(
                i to j + 1,
                i to j - 1,
                i + 1 to j,
                i - 1 to j,
            ).forEach { (i, j) -> fillColor(i, j) }
        }

        fillColor(row, col)

        fun fillColor3(i: Int, j: Int) {
            val currColor = grid.getOrNull(i)?.getOrNull(j)
            if (currColor == null) return
            if (currColor != origColor * -1) return

            val adjacents = listOf(
                i to j + 1,
                i to j - 1,
                i + 1 to j,
                i - 1 to j,
            )

            val isSurrounded = adjacents.count { (i, j) -> grid.getOrNull(i)?.getOrNull(j) == origColor * -1} == 4
            adjacents.forEach { (i, j) -> fillColor3(i, j) }
            if (isSurrounded) grid[i][j] = origColor
            else grid[i][j] = color
        }

        fillColor3(row, col)

        grid.indices.forEach { i ->
            grid[0].indices.forEach { j -> grid[i][j] = abs(grid[i][j]) }
        }

        return grid
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(1, 2, 1, 2, 1, 2),
        intArrayOf(2, 2, 2, 2, 1, 2),
        intArrayOf(1, 2, 2, 2, 1, 2)
    )

    val newBoard = ColorBorder().colorBorder(board, 1, 3, 1)
    println(newBoard)
}