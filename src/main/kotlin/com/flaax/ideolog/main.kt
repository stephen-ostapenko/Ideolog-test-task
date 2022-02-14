package com.flaax.ideolog

import java.io.File

fun processLog(logLines: List<String>): List<String> {
    val dict = mutableMapOf<String, Int>()
    logLines.forEach { line ->
        val tokens = line.split("\\s+".toRegex())
        val threadName = tokens[5]
        val cnt = dict.getOrDefault(threadName, 0) + 1
        dict[threadName] = cnt
    }

    val comp = compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first }
    return dict.toList().sortedWith(comp).take(10).map { "${it.first} ${it.second}" }
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