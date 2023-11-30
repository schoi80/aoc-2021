package com.leetcode

import java.util.PriorityQueue

class LRUCache(capacity: Int) {

    private val capacity = capacity
    private var head: CacheEntry? = null
    private var tail: CacheEntry? = null
    private val cacheMap = mutableMapOf<Int, CacheEntry>()

    class CacheEntry(
        val key: Int,
        var value: Int,
        var prev: CacheEntry? = null,
        var next: CacheEntry? = null,
    ) {
        fun remove() {
            prev?.next = next
            next?.prev = prev
            prev = null
            next = null
        }

        override fun equals(o: Any?) = o is CacheEntry && o.key == key
        override fun hashCode() = key.hashCode()
    }

    fun CacheEntry.moveToHead() {
        if (this == head) return
        if (this == tail) {
            tail = this?.prev
        }
        remove()
        head?.prev = this
        next = head
        head = this
    }

    fun evict() {
        while (cacheMap.size > capacity) {
            val entry = tail?.also {
                println("evicting ${it.key}")
                cacheMap.remove(it.key)
            }
            tail = entry?.prev
            entry?.remove()
        }
    }

    fun get(key: Int): Int {
        return cacheMap[key]?.let { entry ->
            entry.moveToHead()
            entry.value
        } ?: -1
    }

    fun put(key: Int, value: Int) {
        val entry = cacheMap.computeIfAbsent(key) { CacheEntry(key, value) }
        entry.value = value
        entry.moveToHead()
        if (tail == null) tail = head
        evict()
    }
}

fun main() {
//    val cache = LRUCache(2)
//    cache.put(1, 1)
//    cache.put(2, 2)
//    println(cache.get(1))
//    cache.put(3, 3)
//    println(cache.get(2))
//    cache.put(4, 4)
//    println(cache.get(1))
//    println(cache.get(3))
//    println(cache.get(4))

    val cache = LRUCache(1)
    cache.put(2, 1)
    println(cache.get(2))
    cache.put(3, 2)
    println(cache.get(2))
    println(cache.get(3))
}
