package com.leetcode.tree

object LCA {

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun getPath(root: TreeNode?, n: TreeNode): List<TreeNode>? {
        if (root == null) return null
        if (root.`val` == n.`val`) return listOf(root)

        val path = getPath(root.left, n) ?: getPath(root.right, n)
        return path?.let { listOf(root) + it }
    }

    fun lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode? {
        val pPath = getPath(root, p) ?: listOf()
        val qPath = getPath(root, q) ?: listOf()
        return pPath.zip(qPath).takeWhile { (a, b) -> a.`val` == b.`val` }.lastOrNull()?.first
    }
}