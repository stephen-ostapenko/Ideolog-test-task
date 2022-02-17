package com.flaax.ideolog

import java.io.File
import java.io.FileNotFoundException
import java.lang.Exception

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
        println("Missing input file name")
        return
    }

    try {
        val logLines = File(args[0]).readLines()
        val mostFreqThreads = processLog(logLines)

        if (args.size == 1) {
            mostFreqThreads.forEach {
                println(it)
            }
        } else {
            File(args[1]).writeText("")
            mostFreqThreads.forEach {
                File(args[1]).appendText(it + "\n")
            }
            println("Result written to file '${args[1]}'")
        }
    } catch (e: FileNotFoundException) {
        println("Input or output file not found or can't be accessed")
    } catch (e: Exception) {
        println("fatal error!\n" +
                "${e.message}")
    }
}