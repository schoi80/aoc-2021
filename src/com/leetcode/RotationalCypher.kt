package com.fb

fun main() {

    val uc = ('A' .. 'Z')
    val lc = ('a' .. 'z')
    val n = ('0' .. '9')

    fun rotate(c: Char, rot: Int): Char {
        val r = when {
            uc.contains(c) -> uc
            lc.contains(c) -> lc
            n.contains(c) -> n
            else -> return c
        }
        val lb = r.first.code
        val delta = ((c.code - lb) + rot).mod(r.count())
        return (lb + delta).toChar()
    }

    fun rotateCypher(s: String, r: Int): String {
        return s.map { rotate(it, r) }.joinToString("")
    }

    println(rotateCypher("Zebra-493?", 3))
}