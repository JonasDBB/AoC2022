import java.lang.Exception

enum class RPS(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class Outcome(val points: Int) {
    LOSE(0),
    DRAW(3),
    WIN(6)
}

private fun toRps(letter: String) =
    when (letter) {
        "A", "X" -> RPS.ROCK
        "B", "Y" -> RPS.PAPER
        "C", "Z" -> RPS.SCISSORS
        else -> throw Exception("unknown rps letter: $letter")
    }

private fun toOutcome(letter: String) =
    when (letter) {
        "X" -> Outcome.LOSE
        "Y" -> Outcome.DRAW
        "Z" -> Outcome.WIN
        else -> throw Exception("unknown outcome letter: $letter")
    }

private fun outcome1(input: Pair<RPS, RPS>) =
    when (input) {
        // I (right) win
        Pair(RPS.SCISSORS, RPS.ROCK) -> Outcome.WIN.points + RPS.ROCK.points
        Pair(RPS.ROCK, RPS.PAPER) -> Outcome.WIN.points  + RPS.PAPER.points
        Pair(RPS.PAPER, RPS.SCISSORS) -> Outcome.WIN.points + RPS.SCISSORS.points
        // draw
        Pair(RPS.ROCK, RPS.ROCK) -> Outcome.DRAW.points + RPS.ROCK.points
        Pair(RPS.PAPER, RPS.PAPER) -> Outcome.DRAW.points + RPS.PAPER.points
        Pair(RPS.SCISSORS, RPS.SCISSORS) -> Outcome.DRAW.points + RPS.SCISSORS.points
        // opponent (left) wins
        Pair(RPS.PAPER, RPS.ROCK) -> Outcome.LOSE.points + RPS.ROCK.points
        Pair(RPS.SCISSORS, RPS.PAPER) -> Outcome.LOSE.points + RPS.PAPER.points
        Pair(RPS.ROCK, RPS.SCISSORS) -> Outcome.LOSE.points + RPS.SCISSORS.points
        else -> throw Exception("unknown input: $input")
    }

private fun part1(input: List<String>) {
    println(input.sumOf { outcome1(Pair(toRps(it.take(1)), toRps(it.takeLast(1)))) })
}

private fun outcome2(input: Pair<RPS, Outcome>) =
    when (input) {
        // losing
        // rock, mine is scissors
        Pair(RPS.ROCK, Outcome.LOSE) -> Outcome.LOSE.points + RPS.SCISSORS.points
        // paper, mine is rock
        Pair(RPS.PAPER, Outcome.LOSE) -> Outcome.LOSE.points + RPS.ROCK.points
        // scissors, mine is paper
        Pair(RPS.SCISSORS, Outcome.LOSE) -> Outcome.LOSE.points + RPS.PAPER.points
        // draw, means same as opponent
        Pair(RPS.ROCK, Outcome.DRAW) -> Outcome.DRAW.points + RPS.ROCK.points
        Pair(RPS.PAPER, Outcome.DRAW) -> Outcome.DRAW.points + RPS.PAPER.points
        Pair(RPS.SCISSORS, Outcome.DRAW) -> Outcome.DRAW.points + RPS.SCISSORS.points
        // win
        // rock, mine is paper
        Pair(RPS.ROCK, Outcome.WIN) -> Outcome.WIN.points + RPS.PAPER.points
        // paper, mine is scissors
        Pair(RPS.PAPER, Outcome.WIN) -> Outcome.WIN.points + RPS.SCISSORS.points
        // scissors, mine is rock
        Pair(RPS.SCISSORS, Outcome.WIN) -> Outcome.WIN.points + RPS.ROCK.points
        else -> throw Exception("unknown input: $input")
    }

private fun part2(input: List<String>) {
    println(input.sumOf { outcome2(Pair(toRps(it.take(1)), toOutcome(it.takeLast(1)))) })
}

fun main() {
    val input = readInput("02")
    println("part 1")
    part1(input)
    println("part 2")
    part2(input)
}