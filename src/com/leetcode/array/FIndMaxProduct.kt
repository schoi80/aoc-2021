package com.leetcode.array

object FIndMaxProduct {
    fun findMaxProduct(args: Array<Int>): Array<Int> {
        // Write your code here
        val maxTriple = sortedSetOf<Int>()
        var currMaxProduct = 1

        fun addToMax(v: Int) {
            if (maxTriple.size < 3) {
                maxTriple.add(v)
                currMaxProduct *= v
            } else {
                if (maxTriple.first() < v) {
                    val t = maxTriple.first()
                    maxTriple.remove(t)
                    currMaxProduct /= t
                    maxTriple.add(v)
                    currMaxProduct *= v
                }
            }
        }

        val rArray = Array<Int>(args.size) { -1 }

        args.indices.forEach{i ->
            addToMax(args[i])
            if (i >= 2) rArray[i] = currMaxProduct
        }

        return rArray
    }
}

fun main() {
    FIndMaxProduct.findMaxProduct(arrayOf(1)).onEach { println(it) }
}