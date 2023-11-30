package com.leetcode

import com.leetcode.BasicCalculator.calculate

object BasicCalculator {
    fun calculate(s: String): Int {
        var tkns = mutableListOf<String>()
        var si = 0
        for(i in 1 until s.length){
            if(s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/'){
                tkns.add(s.substring(si, i).trim())
                tkns.add("${s[i]}")
                si = i + 1
            }
        }
        tkns.add(s.substring(si, s.length).trim())

        var stk = mutableListOf<Int>()
        var i = 0
        while(i < tkns.count()){
            when(tkns[i]){
                "-" -> {
                    i++
                    stk.add(tkns[i].toInt() * -1)
                } "+" -> {
                i++
                stk.add(tkns[i].toInt())
            } "/" -> {
                i++
                stk[stk.count()-1] /= tkns[i].toInt()
            } "*" -> {
                i++
                stk[stk.count()-1] *= tkns[i].toInt()
            } else -> {
                stk.add(tkns[i].toInt())
            }
            }
            i++
        }

        return stk.sum()
    }
}

fun main() {
    println(calculate("3+ 2*2"))
}