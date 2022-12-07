import java.lang.Exception

data class Dir(val name: String, val parent: Dir?) {
    val subdir = mutableSetOf<Dir>()
    val files = mutableMapOf<String, Int>()
    val size: Int by lazy { subdir.sumOf { it.size } + files.values.sum() }
}

private fun createFS(input: List<String>): MutableSet<Dir> {
    val fileSys = Dir("/", null)
    val dirs = mutableSetOf(fileSys)
    var current = fileSys
    for (line in input) {
        if (line == "$ ls" || line == "$ cd /") {
            continue
        }
        if (line.startsWith("dir")) {
            val words = line.split(" ")
            val newDir = Dir(words[1], current)
            current.subdir.add(newDir)
            dirs.add(newDir)
            continue
        }
        if (line[0].isDigit()) {
            val words = line.split(" ")
            current.files[words[1]] = words[0].toInt()
            continue
        }
        if (line == "$ cd ..") {
            current = current.parent!!
            continue
        }
        if (line.startsWith("$ cd ")) {
            val dirname = line.split(" ")[2]
            current = current.subdir.find { it.name == dirname}!!
            continue
        }
        throw Exception("unknown command $line")
    }
    return dirs
}

private fun part1(dirs: MutableSet<Dir>) {
    println(dirs.filter { it.size <= 100000 }.sumOf { it.size })
}

private fun part2(dirs: MutableSet<Dir>) {
    val spaceStillNeeded = 30000000 - (70000000 - dirs.first().size)
    println(dirs.filter { it.size >= spaceStillNeeded }.minBy { it.size }.size)
}

fun main() {
    val input = readInput("07")
    val dirs = createFS(input)
    println("part 1")
    part1(dirs)
    println("part 2")
    part2(dirs)
}