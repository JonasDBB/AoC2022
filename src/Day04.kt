private fun isRangeContained(r0: List<Int>, r1: List<Int>) =
    r0[0] >= r1[0] && r0[1] <= r1[1] || r1[0] >= r0[0] && r1[1] <= r0[1]

private fun doesRangeOverlap(r0: List<Int>, r1: List<Int>) =
    r0[0] <= r1[0] && r0[1] >= r1[0] || r1[0] <= r0[0] && r1[1] >= r0[0]

private fun part(input: List<List<List<Int>>>, pred: (List<Int>, List<Int>) -> (Boolean)) {
    println(
        input.count { lists ->
            pred(lists[0], lists[1])
        }
    )
}

fun main() {
    // input is list of lines that are first split on "," and those are split on "-"
    val input = readInput("04").map { line ->
        line.split(",").map { range ->
            range.split("-").map { nr -> nr.toInt() }
        }
    }
    println("part 01")
    part(input, ::isRangeContained)
    println("part 02")
    part(input, ::doesRangeOverlap)
}