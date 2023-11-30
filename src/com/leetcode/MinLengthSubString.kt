package com.fb

fun minLengthSubstring(s: String, t: String): Int {
    if (t.length > s.length) return -1

    val tMap = t.groupBy { it }.mapValues { it.value.size }.toMutableMap()

    var i = 0
    var j = 0
    var start = 0
    var end = s.length - 1
    var min = s.length
    var count = t.length
    var found = false
    while (j < s.length) {
        val curr = s[j++]
        if (tMap.containsKey(curr)) {
            tMap[curr] = tMap[curr]!! - 1
            if (tMap[curr]!! >= 0)
                count--
        }

        if (count > 0) continue

        while (count <= 0) {
            val curr = s[i++]
            if (tMap.containsKey(curr)) {
                tMap[curr] = tMap[curr]!! + 1
                if (tMap[curr]!! > 0)
                    count++
            }
        }

        found = true
        val length = j - i
        if (length < min) {
            start = i
            end = j
            min = length
        }
    }

    println(s.substring(start - 1, end))

    return if (!found) -1 else end - start + 1
}

fun main() {
//    println(minLengthSubstring("dcbefebce", "fd"))
    println(minLengthSubstring("dcdefebce", "fd"))
}
