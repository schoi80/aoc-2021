package com.leetcode.tree

import java.util.Scanner

class NodesInSubtree {
    // Tree Node
    class Node {
        var `val`: Int
        var children: MutableList<Node>

        constructor() {
            `val` = 0
            children = ArrayList()
        }

        constructor(_val: Int) {
            `val` = _val
            children = ArrayList()
        }

        constructor(_val: Int, _children: ArrayList<Node>) {
            `val` = _val
            children = _children
        }
    }

    class Query(var u: Int, var c: Char)


    // Add any helper functions you may need here
    fun countOfNodes(root: Node, queries: ArrayList<Query>, s: String): IntArray {

        fun Node.getChar() = s[`val` - 1]

        fun Node.countNodes(c: Char, s: String): Int {
            var count = 0
            val queue = mutableListOf(this)
            while (queue.isNotEmpty()) {
                val n = queue.removeFirst()
                if (n.getChar() == c) count++
                queue.addAll(n.children)
            }
            return count
        }

        // Write your code here
        val nodes = mutableListOf<Node>()
        val queryMap = queries.associate { it.u to it.c }
        val queue = mutableListOf(root)
        while (queryMap.size > nodes.size && queue.isNotEmpty()) {
            val n = queue.removeFirst()
            if (queryMap.containsKey(n.`val`)) {
                nodes.add(n)
            }
            queue.addAll(n.children)
        }

        println("Query nodes found: $nodes")

        return nodes.map { n ->
            val c = queryMap[n.`val`]!!
            n.countNodes(c, s)
        }.toIntArray()
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
        val sc = Scanner(System.`in`)

        //Testcase 1
        val n_1 = 3
        val q_1 = 1
        val s_1 = "aba"
        val root_1: Node = Node(1)
        root_1.children.add(Node(2))
        root_1.children.add(Node(3))
        val queries_1 = ArrayList<Query>()
        queries_1.add(Query(1, 'a'))
        val output_1 = countOfNodes(root_1, queries_1, s_1)
        val expected_1 = intArrayOf(2)
        check(expected_1, output_1)

        // Testcase 2
        val n_2 = 7
        val q_2 = 3
        val s_2 = "abaacab"
        val root_2: Node = Node(1)
        root_2.children.add(Node(2))
        root_2.children.add(Node(3))
        root_2.children.add(Node(7))
        root_2.children[0].children.add(Node(4))
        root_2.children[0].children.add(Node(5))
        root_2.children[1].children.add(Node(6))
        val queries_2 = ArrayList<Query>()
        queries_2.add(Query(1, 'a'))
        queries_2.add(Query(2, 'b'))
        queries_2.add(Query(3, 'a'))
        val output_2 = countOfNodes(root_2, queries_2, s_2)
        val expected_2 = intArrayOf(4, 1, 2)
        check(expected_2, output_2)

        // Add your own test cases here
    }
}

fun main() {
    NodesInSubtree().run()
}
