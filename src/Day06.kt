private fun part(input: String, uniqueLen: Int) {
    for (i in 0 until input.length - uniqueLen) {
        if (input.toCharArray(i, i + uniqueLen).distinct().size == uniqueLen) {
            println(i + uniqueLen)
            return
        }
    }
    println("unique sequence of length $uniqueLen not found")
}

fun main() {
    val input = readInput("06")[0]
    println("part 1")
    part(input, 4)
    println("part 2")
    part(input, 14)
}