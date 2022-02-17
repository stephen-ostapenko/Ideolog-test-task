package com.flaax.ideolog

import java.io.File

const val TOP_LINES_NUMBER = 10

fun processLog(logLines: List<String>): List<String> {
    // map with <name of thread, number of strings>
    val dict = mutableMapOf<String, Int>()
    logLines.forEach { line ->
        val tokens = line.split("\\s+".toRegex())
        // It looks like thread name is the sixth token in each string
        val threadName = tokens[5]
        val cnt = dict.getOrDefault(threadName, 0) + 1
        dict[threadName] = cnt
    }

    val comp = compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first }
    return dict.toList().sortedWith(comp).take(TOP_LINES_NUMBER).map { "${it.first} ${it.second}" }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("missing file name")
        return
    }

    val logLines = File(args[0]).readLines()
    val mostFreqThreads = processLog(logLines)
    mostFreqThreads.forEach {
        println(it)
    }
}