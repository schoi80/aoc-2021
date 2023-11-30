package com.leetcode

class MinRemoveToMakeValid {
    fun minRemoveToMakeValid(s: String): String {
        // contains the index of parenthesis
        val stack = mutableListOf<Int>()
        val newString = CharArray(s.length)
        s.forEachIndexed{i, c ->
            newString[i] = c
            if (!c.isLetter()) {
                if (c == '(') stack.add(i)
                else if (stack.lastOrNull()?.let{s[it]} == '(') stack.removeAt(stack.size - 1)
                else newString[i] = ' '
            }
        }

        stack.forEach{i -> newString[i] = ' '}

        return newString.filter{it != ' '}.joinToString("")
    }
}