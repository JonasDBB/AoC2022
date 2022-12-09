private fun makeArray(input: List<String>): Array<Array<Int>> {
    val arr = Array(input[0].length) { Array<Int>(input.size) { 0 } }
    for (y in input.indices) {
        for (x in input[y].indices) {
            arr[y][x] = input[y][x].toString().toInt()
        }
    }
    return arr
}

private fun isVisible(arr: Array<Array<Int>>, x: Int, y: Int): Boolean {
    if (x == 0 || x == arr[0].size - 1 || y == 0 || y == arr.size - 1) return true
    var visible = true
    for (i in 0 until x) {
        if (arr[y][i] >= arr[y][x]) {
            visible = false
            break
        }
    }
    if (visible) return true
    visible = true
    for (i in x + 1 until arr[x].size) {
        if (arr[y][i] >= arr[y][x]) {
            visible = false
            break
        }
    }
    if (visible) return true
    visible = true
    for (i in 0 until y) {
        if (arr[i][x] >= arr[y][x]) {
            visible = false
            break
        }
    }
    if (visible) return true
    visible = true
    for (i in y + 1 until arr.size) {
        if (arr[i][x] >= arr[y][x]) {
            visible = false
            break
        }
    }
    return visible
}

private fun part1(arr: Array<Array<Int>>) {
    var ret = 0
    for (y in arr.indices) {
        for (x in arr[y].indices) {
            if (isVisible(arr, x, y)) ret += 1
        }
    }
    println(ret)
}

private fun scenicScore(arr: Array<Array<Int>>, x: Int, y: Int): Int {
    var u = 0; var d = 0; var l = 0; var r = 0
    for (i in x - 1 downTo 0 ) {
        l += 1
        if (arr[y][i] >= arr[y][x]) break
    }
    for (i in x + 1 until arr[x].size) {
        r += 1
        if (arr[y][i] >= arr[y][x]) break
    }
    for (i in y - 1 downTo 0) {
        u += 1
        if (arr[i][x] >= arr[y][x]) break
    }
    for (i in y + 1 until arr.size) {
        d += 1
        if (arr[i][x] >= arr[y][x]) break
    }
    return u * d * l * r
}

private fun part2(arr: Array<Array<Int>>) {
    var max = 0
    for (y in arr.indices) {
        for (x in arr[y].indices) {
            val score = scenicScore(arr, x, y)
            if (score > max) max = score
        }
    }
    println(max)
}

fun main() {
    val input = makeArray(readInput("08"))
    println("part 1")
    part1(input)
    println("part 2")
    part2(input)
}