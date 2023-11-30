package com.leetcode.recursion

class EncryptedWords {
    // Add any helper functions you may need here
    fun findEncryptedWord(s: String): String {
        if (s.length <= 1) return s

        val mid = ((s.length + 1) / 2) - 1
        val left = s.substring(0, mid)
        val right = s.substring(mid+1)
        return "${s[mid]}${findEncryptedWord(left)}${findEncryptedWord(right)}"
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    var test_case_number = 1
    fun check(expected: String, output: String) {
        val result = expected == output
        if (result) {
            println("GOOD: Test #$test_case_number")
        } else {
            print("BAD: Test #$test_case_number: Expected ")
            printString(expected)
            print(" Your output: ")
            printString(output)
            println()
        }
        test_case_number++
    }

    fun printString(str: String) {
        print("[\"$str\"]")
    }

    fun run() {
        val s_1 = "abc"
        val expected_1 = "bac"
        val output_1 = findEncryptedWord(s_1)
        check(expected_1, output_1)
        val s_2 = "abcd"
        val expected_2 = "bacd"
        val output_2 = findEncryptedWord(s_2)
        check(expected_2, output_2)

        // Add your own test cases here
    }

}

fun main(args: Array<String>) {
    EncryptedWords().run()
}
