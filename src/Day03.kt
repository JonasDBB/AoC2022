private fun stringHalves(line: String) = Pair(line.substring(0, line.length / 2), line.substring(line.length / 2))

private fun part1(input: List<String>) {
    var ret = 0
    for (line in input) {
        val strings = stringHalves(line)
        run end@ {
            strings.first.forEach { c ->
                if (strings.second.any { it == c }) {
                    ret += if (c.isLowerCase()) c - 'a' + 1 else c - 'A' + 27
                    return@end
                }
            }
        }
    }
    println(ret)
}

private fun part2(input: List<String>) {
    var ret = 0
    for (group in input.chunked(3)) {
        run end@ {
            group[0].forEach { c ->
                if (group[1].any { it == c} && group[2].any { it == c}) {
                    ret += if (c.isLowerCase()) c - 'a' + 1 else c - 'A' + 27
                    return@end
                }
            }
        }
    }
    println(ret)
}

fun main () {
    val input = readInput("03")
    println("part 1")
    part1(input)
    println("part 2")
    part2(input)
}