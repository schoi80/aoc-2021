package com.leetcode

class Solution {

    fun fill(board: Array<CharArray>, i: Int, j:Int) {
        if (board[i][j] != 'O') return

        board[i][j] = '_'

        listOf(
            i to j-1,
            i to j+1,
            i-1 to j,
            i+1 to j
        ).forEach { (i,j) ->
            board.getOrNull(i)?.getOrNull(j)?.let{fill(board, i, j)}
        }
    }

    fun isEdge(board: Array<CharArray>, i: Int, j:Int) : Boolean {
        return i == 0 || i == board.size - 1 || j == 0 || j == board[0].size - 1
    }

    fun solve(board: Array<CharArray>): Unit {
        (board.indices).forEach { i ->
            (0 until board[0].size).forEach {j ->
                if (isEdge(board, i, j)) fill(board, i, j)
            }
        }

        (board.indices).forEach { i ->
            (0 until board[0].size).forEach {j ->
                if (board[i][j] == '_')
                    board[i][j] = 'O'
                else board[i][j] = 'X'
            }
        }
    }
}


fun main() {
    val board = arrayOf(
        "XXXX".toCharArray(),
        "XOOX".toCharArray(),
        "XXOX".toCharArray(),
        "XOXX".toCharArray()
    )
    Solution().solve(board)
    board.onEach { println(it) }
}