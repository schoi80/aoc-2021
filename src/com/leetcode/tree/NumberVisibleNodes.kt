package com.leetcode.tree

import java.io.IOException

class NumberVisibleNodes {
    class Node {
        var data: Int
        var left: Node?
        var right: Node?

        constructor() {
            data = 0
            left = null
            right = null
        }

        constructor(data: Int) {
            this.data = data
            left = null
            right = null
        }
    }

    fun Node.children() : MutableList<Node> {
        return listOfNotNull(left, right).toMutableList()
    }

    fun visibleNodes(root: Node): Int {
        val queue = root.children()
        var count = 1
        while (queue.isNotEmpty()) {
            var queueSize = queue.size
            while (queueSize > 0) {
                queue.addAll(queue.removeFirst().children())
                queueSize--
            }
            count++
        }
        return count
//        fun getVisibleNodeCount(queue: MutableList<Node>): Int {
//            if (queue.isEmpty()) return 0
//            val newQueue = mutableListOf<Node>()
//            while (queue.isNotEmpty()) {
//                newQueue.addAll(queue.removeFirst().children())
//            }
//            return 1 + getVisibleNodeCount(newQueue)
//        }
//        return 1 + getVisibleNodeCount(root.children())
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    var test_case_number = 1
    fun check(expected: Int, output: Int) {
        val result = expected == output
        val rightTick = '\u2713'
        val wrongTick = '\u2717'
        if (result) {
            println("$rightTick Test #$test_case_number")
        } else {
            print("$wrongTick Test #$test_case_number: Expected ")
            printInteger(expected)
            print(" Your output: ")
            printInteger(output)
            println()
        }
        test_case_number++
    }

    fun printInteger(n: Int) {
        print("[$n]")
    }

    @Throws(IOException::class)
    fun run() {
        val root_1 = Node(8)
        root_1.left = Node(3)
        root_1.right = Node(10)
        root_1.left!!.left = Node(1)
        root_1.left!!.right = Node(6)
        root_1.right!!.right = Node(14)
        root_1.left!!.right!!.left = Node(4)
        root_1.left!!.right!!.right = Node(7)
        root_1.right!!.right!!.left = Node(13)
        val expected_1 = 4
        val output_1 = visibleNodes(root_1)
        check(expected_1, output_1)
        val root_2 = Node(10)
        root_2.left = Node(8)
        root_2.right = Node(15)
        root_2.left!!.left = Node(4)
        root_2.left!!.left!!.right = Node(5)
        root_2.left!!.left!!.right!!.right = Node(6)
        root_2.right!!.left = Node(14)
        root_2.right!!.right = Node(16)
        val expected_2 = 5
        val output_2 = visibleNodes(root_2)
        check(expected_2, output_2)

        // Add your own test cases here
    }
}

fun main() {
    NumberVisibleNodes().run()
}