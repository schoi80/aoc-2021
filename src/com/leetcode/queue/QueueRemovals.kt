package com.leetcode.queue

import java.util.LinkedList
import kotlin.math.max

// Add any extra import statements you may need here
class QueueRemovals {
    // Add any helper functions you may need here
    fun findPositions(arr: IntArray, x: Int): IntArray {
        val queue = LinkedList(IntArray(arr.size) { it + 1 }.toList())
        val posArray = IntArray(x) { 0 }
        var runCount = 0
        while (runCount < x) {
            var maxIndex = 0
            val tList = mutableListOf<Int>().apply {
                var maxValue = Int.MIN_VALUE
                repeat(x) {
                    if (queue.isNotEmpty()) {
                        queue.poll().let {
                            if (arr[it - 1] > maxValue) {
                                maxIndex = it
                                maxValue = arr[it - 1]
                            }
                            add(it)
                        }
                    }
                }
            }
            posArray[runCount++] = maxIndex
            tList.forEach {
                if (it != maxIndex) {
                    arr[it - 1] = max(0, arr[it - 1] - 1)
                    queue.add(it)
                }
            }
        }
        return posArray
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    var test_case_number = 1
    fun check(expected: IntArray, output: IntArray) {
        val expected_size = expected.size
        val output_size = output.size
        var result = true
        if (expected_size != output_size) {
            result = false
        }
        for (i in 0 until Math.min(expected_size, output_size)) {
            result = result and (output[i] == expected[i])
        }
        val rightTick = '\u2713'
        val wrongTick = '\u2717'
        if (result) {
            println("$rightTick Test #$test_case_number")
        } else {
            print("$wrongTick Test #$test_case_number: Expected ")
            printIntegerArray(expected)
            print(" Your output: ")
            printIntegerArray(output)
            println()
        }
        test_case_number++
    }

    fun printIntegerArray(arr: IntArray) {
        val len = arr.size
        print("[")
        for (i in 0 until len) {
            if (i != 0) {
                print(", ")
            }
            print(arr[i])
        }
        print("]")
    }

    fun run() {
        val n_1 = 6
        val x_1 = 5
        val arr_1 = intArrayOf(1, 2, 2, 3, 4, 5)
        val expected_1 = intArrayOf(5, 6, 4, 1, 2)
        val output_1 = findPositions(arr_1, x_1)
        check(expected_1, output_1)
        val n_2 = 13
        val x_2 = 4
        val arr_2 = intArrayOf(2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4)
        val expected_2 = intArrayOf(2, 5, 10, 13)
        val output_2 = findPositions(arr_2, x_2)
        check(expected_2, output_2)

        // Add your own test cases here
    }
}

fun main() {
    QueueRemovals().run()
}