package com.leetcode

import java.util.SortedSet

// meeting rooms
fun minMeetingRooms(intervals: Array<IntArray>): Int {
    intervals.sortBy { it.first() }

    val meetingRooms = mutableListOf<Int>()

    intervals.forEach { interval ->
        if (meetingRooms.isEmpty())
            meetingRooms.add(interval[1])
        else {
            meetingRooms
                .firstOrNull { it <= interval[0] }
                ?.let { meetingRooms.remove(it) }
            meetingRooms.add(interval[1])
        }
    }

    return meetingRooms.size
}

fun main() {
    val count = minMeetingRooms(arrayOf(
//        intArrayOf(0, 30),
        intArrayOf(5, 8),
        intArrayOf(6, 8),
    ))
    println(count)
}
