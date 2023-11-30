package com.leetcode.array

class ReverseToMakeEqual {

    // Add any helper functions you may need here
    fun areTheyEqual(a: IntArray, b: IntArray): Boolean {

        fun subarrayEqual(startIndex: Int, endIndex: Int) : Boolean {
            var i = 0
            while (startIndex + i < endIndex) {
                if (a[startIndex + i] != b[endIndex - 1 - i])
                    return false
                i++
            }
            return true
        }

        var i = 0
        while (i < a.size) {
            if (a[i] != b[i]) {
                val startIndex = i
                var found = false

                while (i < a.size) {
                    while (i < a.size && a[i] != b[i]) {
                        i++
                    }
                    if (subarrayEqual(startIndex, i)) {
                        found = true
                        break
                    } else i++
                }
                if (!found) return false
            }
            i++
        }

        return true
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

    fun run() {
        val array_a_1 = intArrayOf(1, 2, 3, 4)
        val array_b_1 = intArrayOf(1, 4, 3, 2)
        val expected_1 = true
        val output_1 = areTheyEqual(array_a_1, array_b_1)
        check(expected_1, output_1)
        val array_a_2 = intArrayOf(1, 2, 3, 4)
        val array_b_2 = intArrayOf(1, 4, 3, 3)
        val expected_2 = false
        val output_2 = areTheyEqual(array_a_2, array_b_2)
        check(expected_2, output_2)
        // Add your own test cases here
        val array_a_3 = intArrayOf(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6)
        val array_b_3 = intArrayOf(1, 6, 5, 4, 3, 2, 1, 6, 5, 4, 3, 2)
        val expected_3 = true
        val output_3 = areTheyEqual(array_a_3, array_b_3)
        check(expected_3, output_3)
    }
}

fun main(args: Array<String>) {
    ReverseToMakeEqual().run()
}