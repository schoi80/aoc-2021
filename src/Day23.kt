import java.util.PriorityQueue
import kotlin.math.abs

typealias Hallway = List<CharArray>

data class State(
    val h: Hallway,
    val cost: Int
) {
    fun copy(cost: Int) : State {
        return State(h.map { it.concatToString().toCharArray() }, cost)
    }
    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is State && this.toString() == other.toString()
    }

    override fun toString() =
        listOf(
            "#############",
            h[0].concatToString(),
            h[1].concatToString(),
            h[2].concatToString(),
            "  #########",
        ).joinToString("\n")

    fun isComplete() = h[1].concatToString() == "###A#B#C#D###" && h[2].concatToString().trim() == "#A#B#C#D#"

    fun possibleMoves(x: Int, y: Int): List<State> {
        val p = h[x][y]

        if (p.isHome(x, y)) return listOf()

        if (x == 2 && h[1][y].isAmphipod()) return listOf()

        val right = (y until h[0].size).takeWhile { h[0][it] == '.' }
            .flatMap { yp ->
                (0..2)
                    .takeWhile { xp -> h[xp][yp] == '.' }
                    .map { xp -> xp to yp }
            }

        val left = (y downTo 0).takeWhile { h[0][it] == '.' }
            .flatMap { yp ->
                (0..2)
                    .takeWhile { xp -> h[xp][yp] == '.' }
                    .map { xp -> xp to yp }
            }

        val moves = (right + left)
            .filterNot { (x, y) -> x == 0 && setOf(3, 5, 7, 9).contains(y) }
            .filterNot { (x, y) -> x == 1 && h[2][y] != p }

        return (moves.find { (x, y) -> p.isHome(x, y) }?.let { listOf(it) } ?: moves)
            .map { (xp, yp) ->
                val cost = (x + abs(yp - y) + xp) * p.cost()
                val newState = this.copy(this.cost + cost)
                newState.h[x][y] = '.'
                newState.h[xp][yp] = p
                newState
            }
    }
}

fun Char.isAmphipod() = "ABCD".contains(this)

fun Char.isHome(x: Int, y: Int) = x > 0 && when (this) {
    'A' -> y == 3
    'B' -> y == 5
    'C' -> y == 7
    'D' -> y == 8
    else -> error("blip!")
}

fun Char.cost() = when (this) {
    'A' -> 1
    'B' -> 10
    'C' -> 100
    'D' -> 1000
    else -> error("blip")
}

fun main() {
    val state = readInput("test").subList(1, 4).map { it.toCharArray() }.let { State(it, 0) }
    val costMap = mutableMapOf<State, Int>().withDefault { Int.MAX_VALUE }
    val minCostQueue = PriorityQueue<State>(compareBy { it.cost })
    val visited = mutableSetOf<State>()
    costMap[state] = 0
    minCostQueue.add(state)

    while (true) {
        val s = minCostQueue.remove()
        if (visited.contains(s)) continue
        println("Curr cost: ${s.cost}")
        if (s.isComplete()) {
            println(s)
            break
        }
        (0..2).forEach { i ->
            s.h[i].forEachIndexed { j, c ->
                if (c.isAmphipod()) {
                    s.possibleMoves(i, j).map { ns ->
                        if (ns.cost + s.cost < costMap.getValue(ns)) {
                            minCostQueue.remove(ns)
                            minCostQueue.add(ns)
                            costMap[ns] = ns.cost
                        }
                    }
                }
            }
        }
        visited.add(s)
    }
}