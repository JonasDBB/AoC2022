private fun splitListOnEmpty(lst: List<Int?>): List<List<Int>> {
    val ret: MutableList<List<Int>> = mutableListOf()
    val sublist: MutableList<Int> = mutableListOf()
    for (i in lst.indices) {
        if (lst[i] == null) {
            if (sublist.isNotEmpty()) ret.add(sublist.toList())
            sublist.clear()
            continue
        }
        sublist.add(lst[i]!!)
    }
    if (sublist.isNotEmpty()) ret.add(sublist.toList())
    return ret
}

private fun part1(nrs: List<List<Int>>) {
    println(nrs.maxOfOrNull { it.sum() } ?: 0)
}

private fun part2(nrs: List<List<Int>>) {
    val mutNrs: MutableList<Int> = nrs.map { it.sum() }.toMutableList()
    mutNrs.sort()
    println(mutNrs.takeLast(3).sum())
}

fun main() {
    val listOfIntLists = splitListOnEmpty(readInput("01").map { it.toIntOrNull() })
    println("part 1")
    part1(listOfIntLists)
    println("part 2")
    part2(listOfIntLists)
}
