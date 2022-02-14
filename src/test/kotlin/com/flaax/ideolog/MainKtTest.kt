package com.flaax.ideolog

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class MainKtTest {

    @Test
    fun testSampleLog() {
        val logLines = File("src/test/resources/hz_all_nodes.log").readLines()
        val answer = listOf(
            "hz.gifted_pare.priority-generic-operation.thread-0 2490",
            "hz.serene_heisenberg.priority-generic-operation.thread-0 1952",
            "hz.serene_heisenberg.generic-operation.thread-15 291",
            "hz.serene_heisenberg.generic-operation.thread-0 285",
            "jettyThreadPool-180 52",
            "jettyThreadPool-184 46",
            "jettyThreadPool-185 44",
            "jettyThreadPool-181 43",
            "jettyThreadPool-179 37",
            "jettyThreadPool-183 37"
        )

        val result = processLog(logLines)
        assertIterableEquals(answer, result)
    }

    @Test
    fun testSmallLog1() {
        val logLines = File("src/test/resources/small_log_1.log").readLines()
        val answer = listOf(
            "hz.friendly_pasteur.cached.thread-10 1",
            "hz.gifted_pare.partition-operation.thread-16 1",
            "hz.gifted_pare.partition-operation.thread-17 1",
            "hz.serene_heisenberg.partition-operation.thread-17 1",
            "hz.serene_heisenberg.partition-operation.thread-19 1"
        )

        val result = processLog(logLines)
        assertIterableEquals(answer, result)
    }

    @Test
    fun testSmallLog2() {
        val logLines = File("src/test/resources/small_log_2.log").readLines()
        val answer = listOf(
            "hz.serene_heisenberg.generic-operation.thread-15 12",
            "jettyThreadPool-184 2",
            "ForkJoinPool.commonPool-worker-47 1",
            "ForkJoinPool.commonPool-worker-51 1",
            "hz.friendly_pasteur.cached.thread-8 1",
            "hz.friendly_pasteur.migration 1",
            "hz.gifted_pare.generic-operation.thread-5 1",
            "hz.gifted_pare.generic-operation.thread-6 1",
            "hz.gifted_pare.priority-generic-operation.thread-0 1",
            "hz.serene_heisenberg.InvocationMonitorThread 1"
        )

        val result = processLog(logLines)
        assertIterableEquals(answer, result)
    }

}