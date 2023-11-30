package com.leetcode

import kotlin.math.max
import kotlin.math.min

object FbSolution {

    fun isPalindrome(s: String): Boolean {
        if (s.isEmpty()) return true

        var i = 0
        var j = s.length - 1

        fun moveStart() {
            while (!s[i].isLetterOrDigit() && i < j) i++
        }

        fun moveBack() {
            while (!s[j].isLetterOrDigit() && i < j) j--
        }

        while (true) {
            moveStart()
            moveBack()
            val ci = s[i].toLowerCase()
            val cj = s[j].toLowerCase()
            if (ci != cj) return false
            i++
            j--
            if (i > j) break
        }
        return true
    }

    fun validWordAbbreviation(word: String, abbr: String): Boolean {
        var i = 0
        var j = 0
        while (i < word.length && j < abbr.length) {
            if (abbr[j].isLetter()) {
                if (word[i] != abbr[j]) return false
                i++
                j++
            } else {
                if (abbr[j] == '0') return false
                val start = j
                while (j < abbr.length && abbr[j].isDigit()) j++
                val advance = abbr.substring(start, j).toInt()
                i += advance
            }
        }
        return i == word.length && j == abbr.length
    }

    fun addStrings(num1: String, num2: String): String {
        val answer = CharArray(max(num1.length, num2.length))
        var hasCarry = false
        var i = 0
        while (i < answer.size) {
            val n1 = num1.length - 1 - i
            val n2 = num2.length - 1 - i
            val ai = answer.size - 1 - i
            val v1 = num1.getOrNull(n1)?.digitToInt() ?: 0
            val v2 = num2.getOrNull(n2)?.digitToInt() ?: 0
            val sum = v1 + v2 + if (hasCarry) 1 else 0
            answer[ai] = sum.mod(10).digitToChar()
            hasCarry = sum >= 10
            i++
        }
        val result = answer.joinToString("")
        return if (hasCarry) "1$result" else result
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        val nodeMap = mutableMapOf<Int, Int>()
        fun getDepth(n: TreeNode?): Int {
            if (n == null) return 0
            if (n.left == null && n.right == null) return 0
            val leftDepth = n.left?.let { getDepth(it) + 1 } ?: 0
            val rightDepth = n.right?.let { getDepth(it) + 1 } ?: 0
            val depth = max(leftDepth, rightDepth)
            nodeMap[n.`val`] = leftDepth + rightDepth
            return depth
        }

        getDepth(root)
        return nodeMap.maxOf { it.value }
    }

    fun validPalindrome(s: String, allowRemoval: Boolean = true): Boolean {
        var i = 0
        var j = s.length - 1
        while (i < j) {
            val a = s[i]
            val b = s[j]
            if (a != b) {
                return if (allowRemoval)
                    validPalindrome(s.substring(i, j), false) || validPalindrome(s.substring(i + 1, j + 1), false)
                else false
            } else {
                i++
                j--
            }
        }
        return true
    }

    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        if (matrix.size == 1) return true
        fun isToeplitz(v: Int, i: Int, j: Int): Boolean {
            return matrix.getOrNull(i)?.getOrNull(j)?.let { it == v && isToeplitz(v, i + 1, j + 1) } ?: true
        }
        (matrix[0].indices).forEach { j ->
            if (!isToeplitz(matrix[0][j], 0, j)) return false
        }
        (1 until matrix.size).forEach { i ->
            if (!isToeplitz(matrix[i][0], i, 0)) return false
        }
        return true
    }

    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val alphabets = order.mapIndexed { i, c -> c to i }.toMap()

        fun List<Char?>.isSorted(): Boolean {
            this.map { alphabets[it] ?: Int.MIN_VALUE }.zipWithNext().forEach { (a, b) -> if (a > b) return false }
            return true
        }

        fun isSorted(words: List<String>, index: Int): Boolean {
            val cArray = words.map { it.getOrNull(index) }
            if (!cArray.isSorted()) return false
            cArray.mapIndexed { i, c -> c to i }
                .groupBy { it.first }
                .filter { it.value.size > 1 }
                .forEach {
                    if (!isSorted(it.value.map { words[it.second] }, index + 1)) return false
                }
            return true
        }

        val distinctWords = words.toList().fold(mutableListOf<String>()) { agg, it ->
            if (agg.lastOrNull() != it) agg.add(it)
            agg
        }

        return isSorted(distinctWords, 0)
    }

    fun isAlienSortedV2(words: Array<String>, order: String): Boolean {
        val alphabets = order.mapIndexed { i, c -> c to i }.toMap()
        val comparator = Comparator<String> { o1, o2 ->
            var i = 0
            val j = min(o1.length, o2.length)
            while (i < j && o1[i] == o2[i]) i++
            val orderA = alphabets[o1.getOrNull(i)] ?: Int.MIN_VALUE
            val orderB = alphabets[o2.getOrNull(i)] ?: Int.MIN_VALUE
            when {
                orderA < orderB -> -1
                orderA > orderB -> 1
                else -> 0
            }
        }

        words.sortedArrayWith(comparator).zip(words).forEach { (a, b) ->
            if (a != b) return false
        }
        return true
    }

    fun removeDuplicates(s: String): String {
        val cList = mutableListOf<Char>()
        var i = 0
        var shouldRemove = false
        while (i < s.length) {
            if (shouldRemove) {
                cList.removeAt(cList.size - 1)
                shouldRemove = false
            }
            val c = s[i++]
            if (cList.lastOrNull() == c)
                shouldRemove = true
            else cList.add(c)
        }

        if (shouldRemove) cList.removeAt(cList.size - 1)
        return cList.joinToString("")
    }

//    data class Entry (
//        val key: Int
//            ) {
//        val t = Instant.now().ep
//        val m = mutableMapOf<Int, Int>().wi
//        override fun equals(other: Any?): Boolean {
//            return other is Entry
//        }
//    }


    fun overlap(a: IntArray, b: IntArray): Boolean {
        return when {
            a[0] <= b[0] && b[1] <= a[1] ||
                    b[0] <= a[0] && a[1] <= b[1] ||
                    a[0] <= b[0] && b[0] <= a[1] ||
                    a[0] <= b[1] && b[1] <= a[1] -> true
            else -> false
        }
    }

    fun mergePair(a: IntArray, b: IntArray): IntArray {
        val r = IntArray(2)
        r[0] = min(a[0], b[0])
        r[1] = max(a[1], b[1])
        return r
    }


    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        intervals.forEach { interval ->
            val last = result.last()
            if (last == null) result.add(interval)
            else {
                if (overlap(last, interval)) {
                    result.removeAt(result.size - 1)
                    result.add(mergePair(last, interval))
                } else result.add(interval)
            }
        }
        return result.toTypedArray()
    }


}

fun main() {
//    println(FbSolution.subarraySum(intArrayOf(1, 2, 3), 3))
//    println(FbSolution.isPalindrom("A man, a plan, a canal: Panama"))
//    println(FbSolution.isPalindrom("Race a car"))
//    println(FbSolution.isPalindrom(" "))

//    println(FbSolution.validWordAbbreviation("internationalization", "i12iz4n"))
//    println(FbSolution.validWordAbbreviation("substitution", "s10n"))
//    println(FbSolution.validWordAbbreviation("substitution", "sub4u4"))
//    println(FbSolution.validWordAbbreviation("substitution", "12"))
//    println(FbSolution.validWordAbbreviation("substitution", "su3i1u2on"))
//    println(FbSolution.validWordAbbreviation("substitution", "substitution"))
//    println(FbSolution.validWordAbbreviation("substitution", "s55n"))
//    println(FbSolution.validWordAbbreviation("substitution", "s010n"))
//    println(FbSolution.validWordAbbreviation("substitution", "s0ubstitution"))
//    println(FbSolution.addStrings("11", "123"))
//    println(FbSolution.addStrings("99", "1"))
//
//    println(FbSolution.validPalindrome("aba"))
//    println(FbSolution.validPalindrome("abca"))
//    println(FbSolution.validPalindrome("abc"))
//    println(FbSolution.validPalindrome("ebcbbececabbacecbbcbe"))
//
//    println(FbSolution.isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz"))
//    println(FbSolution.isAlienSorted(arrayOf("hello", "hello"), "hlabcdefgijkmnopqrstuvwxyz"))
//    println(FbSolution.isAlienSorted(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz"))

//    println(FbSolution.removeDuplicates("abbaca"))
}