package com.leetcode.array

object WinnerTTT {

    val winningMoves = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6),
    ).flatMap { pos -> pos.map { it to pos } }
        .groupBy { it.first }
        .mapValues { it.value.map { it.second } }

    fun tictactoe(moves: Array<IntArray>): String {
        val board = Array(9) { "" }
        moves.forEachIndexed { i, loc ->
            val player = if (i.rem(2) == 1) "B" else "A"
            val index = loc[0]*3 + loc[1]
            board[index] = player
            winningMoves[index]!!.forEach { winningMove ->
                val state = winningMove.joinToString("") { board[it] }
                if (state == player.repeat(3))
                    return player
            }
        }
        return if (moves.size < 9) "Pending" else "Draw"
    }
}

fun main() {
    val moves = arrayOf<IntArray>(
        intArrayOf(0, 0),
        intArrayOf(1, 1),
        intArrayOf(0, 1),
        intArrayOf(0, 2),
        intArrayOf(1, 0),
        intArrayOf(2, 0),
    )
    println(WinnerTTT.tictactoe(moves))
}