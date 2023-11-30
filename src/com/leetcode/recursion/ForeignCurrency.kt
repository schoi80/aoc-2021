package com.leetcode.recursion

class ForeignCurrency {
    // Add any helper functions you may need here
    fun canGetExactChange(targetMoney: Int, denominations: IntArray): Boolean {
        val rem = denominations.map { targetMoney - it }.filter { it >= 0 }
        return if (rem.isEmpty()) false
        else if (rem.contains(0)) true
        else rem.map { canGetExactChange(it, denominations) }.contains(true)
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
        print("[\"$str\"]")
    }

    fun run() {
        val target_1 = 94
        val arr_1 = intArrayOf(5, 10, 25, 100, 200)
        val expected_1 = false
        val output_1 = canGetExactChange(target_1, arr_1)
        check(expected_1, output_1)
        val target_2 = 75
        val arr_2 = intArrayOf(4, 17, 29)
        val expected_2 = true
        val output_2 = canGetExactChange(target_2, arr_2)
        check(expected_2, output_2)

        // Add your own test cases here
    }

}

fun main() {
    ForeignCurrency().run()
}
