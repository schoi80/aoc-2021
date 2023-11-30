package com.leetcode.tree

import java.util.SortedSet
import kotlin.math.pow

object LCA2 {

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
        var parent: TreeNode? = null
    }
//
//    fun getPath(root: TreeNode?, n: TreeNode): List<TreeNode>? {
//        if (n == null) return null
//        val path = getPath(n.parent) ?: listOf()
//        return path + listOf(n)
//    }
//
//    fun lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode? {
//        val pPath = getPath(root, p) ?: listOf()
//        val qPath = getPath(root, q) ?: listOf()
//        return pPath.zip(qPath).takeWhile { (a, b) -> a.`val` == b.`val` }.lastOrNull()?.first
//    }

}

val a = sortedSetOf(3,2,1)
fun main() {
    var c = 10
    c /= 2
    println(a.first())
    val b = Array<Int>(3){-1}
}