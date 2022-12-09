import java.lang.Exception

data class Loc(val x: Int, val y: Int)

enum class Direction {
    up,
    down,
    left,
    right
}

private fun move(c: Loc, d: Direction) =
    when (d) {
        Direction.up -> Loc(c.x, c.y -1)
        Direction.down -> Loc(c.x, c.y + 1)
        Direction.left -> Loc(c.x - 1, c.y)
        Direction.right -> Loc(c.x + 1, c.y)
    }

private fun moveTail(h: Loc, t: Loc): Loc {
    if (t.x < h.x && t.y < h.y - 1 ||
        t.x < h.x - 1 && t.y < h.y) { // down right
        return move(move(t, Direction.down), Direction.right)
    }
    if (t.x > h.x && t.y < h.y - 1 ||
        t.x > h.x + 1 && t.y < h.y) { // down left
        return move(move(t, Direction.down), Direction.left)
    }
    if (t.x < h.x && t.y > h.y + 1 ||
        t.x < h.x - 1 && t.y > h.y) { // up right
        return move(move(t, Direction.up), Direction.right)
    }
    if (t.x > h.x && t.y > h.y + 1 ||
        t.x > h.x + 1 && t.y > h.y) { // up left
        return move(move(t, Direction.up), Direction.left)
    }
    if (t.x < h.x - 1) { // right
        return move(t, Direction.right)
    }
    if (t.x > h.x + 1) { // left
        return move(t, Direction.left)
    }
    if (t.y < h.y - 1) { // down
        return move(t, Direction.down)
    }
    if (t.y > h.y + 1) { // up
        return move(t, Direction.up)
    }
    return t
}

private fun part1(input: List<String>) {
    var head = Loc(0, 0)
    var tail = Loc(0, 0)
    val tailLocations = mutableSetOf(tail)
    for (line in input) {
        val spl = line.split(" ")
        for (i in 0 until spl[1].toInt()) {
            head = when (spl[0]) {
                "U" -> move(head, Direction.up)
                "D" -> move(head, Direction.down)
                "L" -> move(head, Direction.left)
                "R" -> move(head, Direction.right)
                else -> throw Exception("bad input: $line")
            }
            tail = moveTail(head, tail)
            tailLocations.add(tail)
        }
    }
    println(tailLocations.size)
}

private fun part2(input: List<String>) {
    val knots = MutableList(10) { Loc(0, 0) }
    val tailLocations = mutableSetOf(knots[9])
    for (line in input) {
        val spl = line.split(" ")
        for (i in 0 until spl[1].toInt()) {
            knots[0] = when (spl[0]) {
                "U" -> move(knots[0], Direction.up)
                "D" -> move(knots[0], Direction.down)
                "L" -> move(knots[0], Direction.left)
                "R" -> move(knots[0], Direction.right)
                else -> throw Exception("bad input: $line")
            }
            for (j in 1..9) {
                knots[j] = moveTail(knots[j - 1], knots[j])
            }
            tailLocations.add(knots[9])
        }
    }
    println(tailLocations.size)
}

fun main() {
    val input = readInput("09")
    println("part 1")
    part1(input)
    println("part 2")
    part2(input)
}