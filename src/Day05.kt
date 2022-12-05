import java.util.Stack

private fun setupStacks(input: List<String>): MutableList<Stack<Char>> {
    val listLineI = input.indexOfFirst { it[1] == '1' }
    var nrOfStacks = input[listLineI].count { !it.isWhitespace() }
    // this only works until 99 stacks, but input only goes to 9 anyway so w/e
    if (nrOfStacks > 10) nrOfStacks -= (nrOfStacks - 10) / 2 + 1
    val stacks: MutableList<Stack<Char>> = mutableListOf()
    for (i in 0 until nrOfStacks) stacks.add(Stack<Char>())
    for (i in listLineI - 1 downTo 0) {
        for (j in 1..(nrOfStacks * 4 - 2) step 4) {
            if (j >= input[i].length)
                break
            if (!input[i][j].isWhitespace()) {
                stacks[((j - 1) / 4)].push(input[i][j])
            }
        }
    }
    return stacks
}

private fun part1(input: List<String>, stacks: MutableList<Stack<Char>>) {
    val firstMove = input.indexOfFirst { it.startsWith("move") }
    for (i in firstMove until input.size) {
        val words = input[i].split(" ")
        val src = words[3].toInt() - 1
        val dst = words[5].toInt() - 1
        for (j in 0 until words[1].toInt()) {
            stacks[dst].push(stacks[src].pop())
        }
    }
    for (stk in stacks) print(stk.peek())
    println()
}

// lots of duplication, but didn't feel like making it better now
private fun part2(input: List<String>, stacks: MutableList<Stack<Char>>) {
    val firstMove = input.indexOfFirst { it.startsWith("move") }
    for (i in firstMove until input.size) {
        val words = input[i].split(" ")
        // probably very inefficient, but I don't care atm
        val tmp = Stack<Char>()
        val src = words[3].toInt() - 1
        val dst = words[5].toInt() - 1
        for (j in 0 until words[1].toInt()) {
            tmp.push(stacks[src].pop())
        }
        for (j in 0 until words[1].toInt()) {
            stacks[dst].push(tmp.pop())
        }
    }
    for (stk in stacks) print(stk.peek())
    println()
}

fun main() {
    val input = readInput("05")
    println("part 1")
    part1(input, setupStacks(input))
    println("part 2")
    // re make stacks so they are reset to the input
    part2(input, setupStacks(input))
}