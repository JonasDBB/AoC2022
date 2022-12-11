private fun part1(input: List<String>) {
    var c = 0
    var xreg = 1
    var ret = 0
    for (line in input) {
        if (line == "noop") {
            ++c
            if ((c + 20) % 40 == 0) {
                ret += c * xreg
            }
            continue
        }
        val inc = line.split(" ")[1].toInt()
        ++c
        if ((c + 20) % 40 == 0) {
            ret += c * xreg
        }
        ++c
        if ((c + 20) % 40 == 0) {
            ret += c * xreg
        }
        xreg += inc
    }
    println(ret)
}

private fun part2(input: List<String>) {
    val screen = MutableList(6) { CharArray(40) }
    var c = 0
    var xreg = 1
    for (line in input) {
        if (line == "noop") {
            val chr = if (c % 40 == xreg || c % 40 == xreg - 1 || c % 40 == xreg + 1) '#' else '.'
            screen[c / 40][c % 40] = chr
            ++c
        } else {
            val inc = line.split(" ")[1].toInt()
            var chr = if (c % 40 == xreg || c % 40 == xreg - 1 || c % 40 == xreg + 1) '#' else '.'
            screen[c / 40][c % 40] = chr
            ++c
            chr = if (c % 40 == xreg || c % 40 == xreg - 1 || c % 40 == xreg + 1) '#' else '.'
            screen[c / 40][c % 40] = chr
            ++c
            xreg += inc
        }
    }
    screen.forEach { line -> line.forEach { print("$it ") }; println() }
}

fun main() {
    val input = readInput("10")
    println("part 1")
    part1(input)
    println("part 2")
    part2(input)
}
