package com.leetcode

object RemoveInvalidParenthesis {

    fun fixUnbalanced(s: String): List<String> {
        val s = s.take(s.length - s.takeLastWhile { it == '(' }.length)
        return if (s.contains(')')) {
            (0..s.length - 2)
                .filter { s[it] == ')' }
                .map { s.substring(0, it) + s.substring(it + 1) }
        } else listOf(s)
    }

    fun findUnbalanceIndex(s: String): Int {
        if (s.isBlank()) return 1
        val q = mutableListOf<Char>()
        var i = 0
        while (i < s.length) {
            if (s[i] == '(' || s[i] == ')') {
                if (s[i] == '(') q.add(s[i])
                else if (q.lastOrNull() == '(') q.removeAt(q.size - 1)
                else return i
            }
            i++
        }
        return if (q.isNotEmpty()) s.length - q.size else i
    }

    fun removeInvalidParentheses(s: String): List<String> {
        val index = findUnbalanceIndex(s)
        return if (index < s.length) {
            val prefixes = fixUnbalanced(s.substring(0, index + 1))
            prefixes.flatMap { prefix ->
                removeInvalidParentheses(s.substring(index + 1)).map {
                    prefix + it
                }
            }.takeIf { it.isNotEmpty() } ?: listOf("")
        } else listOf(s)
    }

}

fun main() {
    println(RemoveInvalidParenthesis.removeInvalidParentheses("((())"))
    println(RemoveInvalidParenthesis.removeInvalidParentheses("x("))
    println(RemoveInvalidParenthesis.removeInvalidParentheses("((("))
    println(RemoveInvalidParenthesis.removeInvalidParentheses("()())()"))
    println(RemoveInvalidParenthesis.removeInvalidParentheses("(a)())()"))
    println(RemoveInvalidParenthesis.removeInvalidParentheses(")("))
}