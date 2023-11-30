package com.leetcode

import kotlin.math.abs

// Word Search II
fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    val cMap = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
    board.indices.forEach { i ->
        board[i].indices.forEach { j ->
            cMap.computeIfAbsent(board[i][j]) { mutableListOf() }.add(i to j)
        }
    }

    fun findWord(
        word: String,
        currPos: Pair<Int, Int>? = null,
        visited: Array<BooleanArray> = Array(board.size) { BooleanArray(board[0].size) { false } }
    ): Boolean {
        if (word.isBlank()) return true
        val nextMoves = cMap[word[0]]?.filter {
            !visited[it.first][it.second] && currPos?.let { (i, j) ->
                (abs(i - it.first) + abs(j - it.second)) == 1
            } ?: true
        } ?: listOf()
        nextMoves.forEach { curr ->
            visited[curr.first][curr.second] = true
            if (findWord(word.substring(1), curr, visited))
                return true
            visited[curr.first][curr.second] = false
        }
        return false
    }

    return words.filter { findWord(it) }
}

fun main() {
//    val result = findWords(
//        board = arrayOf(
//            charArrayOf('a', 'a'),
//        ),
//        words = arrayOf("aaa")
//    )
    val result = findWords(
        board = arrayOf(
            charArrayOf('o', 'a', 'a', 'n'),
            charArrayOf('e', 't', 'a', 'e'),
            charArrayOf('i', 'h', 'k', 'r'),
            charArrayOf('i', 'f', 'l', 'v'),
        ),
        words = arrayOf("oath", "pea", "eat", "rain")
    )
    result.onEach { println(it) }
}
