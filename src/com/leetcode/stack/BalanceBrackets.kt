package com.leetcode.stack

import java.util.LinkedList
import java.util.PriorityQueue

class BalanceBrackets {

    fun Char.isMatching(c: Char) = when {
        this == '(' && c == ')' ||
                this == '[' && c == ']' ||
                this == '{' && c == '}' -> true
        else -> false
    }

    // Add any helper functions you may need here
    fun isBalanced(s: String): Boolean {
        // Write your code here
        val stack = LinkedList<Char>()
        s.forEach {
            if (stack.isEmpty()) stack.add(it)
            else {
                if (stack.peekLast().isMatching(it)) stack.pollLast()
                else stack.add(it)
            }
        }

        return stack.isEmpty()
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    var test_case_number = 1
    fun check(expected: Boolean, output: Boolean) {
        val result = expected == output
        val rightTick = '\u2713'
        val wrongTick = '\u2717'
        if (result) {
            println("$rightTick Test #$test_case_number")
        } else {
            print("$wrongTick Test #$test_case_number: Expected ")
            print(expected)
            print(" Your output: ")
            print(output)
            println()
        }
        test_case_number++
    }

    fun printString(str: String) {
        print("[$str]")
    }

    fun run() {
        val s_1 = "{[(])}"
        val expected_1 = false
        val output_1 = isBalanced(s_1)
        check(expected_1, output_1)
        val s_2 = "{{[[(())]]}}"
        val expected_2 = true
        val output_2 = isBalanced(s_2)
        check(expected_2, output_2)
        val s_3 = "{(})"
        val expected_3 = false
        val output_3 = isBalanced(s_3)
        check(expected_3, output_3)
        val s_4 = ")"
        val expected_4 = false
        val output_4 = isBalanced(s_4)
        check(expected_4, output_4)
    }
}

fun main() {
    BalanceBrackets().run()
}