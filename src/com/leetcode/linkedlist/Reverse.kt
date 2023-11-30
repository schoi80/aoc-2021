package com.leetcode.linkedlist

// Add any extra import statements you may need here
class Reverse {
    class Node(var data: Int) {
        var next: Node? = null
    }

    // Add any helper functions you may need here
    fun reverse(head: Node?): Node? {
        // Write your code here
        var h = head
        var c = head
        var p: Node? = null
        var n: Node? = null
        var rp: Node? = null
        while (c != null) {
            if (c.data.mod(2) == 1) {
                p = c
                c = c.next
            } else {
                rp = null
                while (c != null && c.data.mod(2) == 0) {
                    n = c.next
                    c.next = rp
                    rp = c
                    c = n
                }
                if (p == null) {
                    h?.next = c
                    h = rp
                } else {
                    p.next?.next = c
                    p.next = rp
                }
            }
        }
        return h
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    var test_case_number = 1
    fun printLinkedList(head: Node?) {
        var head = head
        print("[")
        while (head != null) {
            print(head.data)
            head = head.next
            if (head != null) print(" ")
        }
        print("]")
    }

    fun check(expectedHead: Node?, outputHead: Node?) {
        var expectedHead = expectedHead
        var outputHead = outputHead
        var result = true
        val tempExpectedHead = expectedHead
        val tempOutputHead = outputHead
        while (expectedHead != null && outputHead != null) {
            result = result and (expectedHead.data == outputHead.data)
            expectedHead = expectedHead.next
            outputHead = outputHead.next
        }
        if (!(expectedHead == null && outputHead == null)) result = false
        val rightTick = '\u2713'
        val wrongTick = '\u2717'
        if (result) {
            println("$rightTick Test #$test_case_number")
        } else {
            print("$wrongTick Test #$test_case_number: Expected ")
            printLinkedList(tempExpectedHead)
            print(" Your output: ")
            printLinkedList(tempOutputHead)
            println()
        }
        test_case_number++
    }

    fun createLinkedList(arr: IntArray): Node {
        var head: Node? = null
        var tempHead = head
        for (v in arr) {
            if (head == null) {
                head = Node(v)
                tempHead = head
            } else {
                head.next = Node(v)
                head = head.next
            }
        }
        return tempHead!!
    }

    fun run() {
        val arr_1 = intArrayOf(1, 2, 8, 9, 12, 16)
        val expected1 = intArrayOf(1, 8, 2, 9, 16, 12)
        val head_1 = createLinkedList(arr_1)
        val expected_1 = createLinkedList(expected1)
        val output_1 = reverse(head_1)
        check(expected_1, output_1)
        val arr_2 = intArrayOf(2, 18, 24, 3, 5, 7, 9, 6, 12)
        val expected2 = intArrayOf(24, 18, 2, 3, 5, 7, 9, 12, 6)
        val head_2 = createLinkedList(arr_2)
        val expected_2 = createLinkedList(expected2)
        val output_2 = reverse(head_2)
        check(expected_2, output_2)

        // Add your own test cases here
    }
}

fun main(args: Array<String>) {
    Reverse().run()
}