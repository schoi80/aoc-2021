package com.leetcode.tree

import java.util.LinkedList

object BinaryTreeVerticalOrderTraversal {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    data class ColumnNode(
        val col: Int,
        val depth: Int,
        val node: TreeNode
    )

    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        val q = LinkedList<ColumnNode>()
        val r = mutableListOf<ColumnNode>()
        if (root != null) q.add(ColumnNode(0, 0, root))
        while (q.isNotEmpty()) {
            val c = q.poll()
            r.add(c)
            c.node.left?.let{ q.add(ColumnNode(c.col - 1, c.depth + 1, it)) }
            c.node.right?.let{ q.add(ColumnNode(c.col + 1, c.depth + 1, it)) }
        }

        return r.groupBy { it.col }
            .toSortedMap()
            .mapValues { it.value.sortedBy { it.depth }.map { it.node.`val` } }
            .values
            .toList()
    }
}