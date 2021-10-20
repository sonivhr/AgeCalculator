package com.agecalculator

import org.junit.Test
import kotlin.system.measureTimeMillis

class InterviewTest {

    @Test
    fun testSortSetsInsideAndOutside() {
        val arrayOfSets = mutableListOf(
            setOf("2", "1", "4"),
            setOf("4", "5", "2"),
            setOf("8", "0", "4"),
            setOf("3", "17", "36"),
            setOf("2", "1", "3"),
            setOf("0", "1", "2")
        )
        val runtimeInMillis = measureTimeMillis { sortSetsInsideAndOutside(arrayOfSets) }
        println("Run time in millis: $runtimeInMillis")
    }

    private fun sortSetsInsideAndOutside(arrayOfSets: MutableList<Set<String>>) {
        println("Original list: $arrayOfSets")
        for (i in 0 until arrayOfSets.size) {
            arrayOfSets[i] = arrayOfSets[i].sortedWith(
                Comparator { first, next ->
                    return@Comparator first.toInt() - next.toInt()
                }
            ).toSet()
        }
        println("Sorted set/inside: $arrayOfSets")
        val newSortedList = arrayOfSets.sortedWith(
            Comparator { firstSet, nextSet ->
                val firstSetIterator = firstSet.iterator()
                val nextSetIterator = nextSet.iterator()
                var eachSetItemDiff = 0
                while (firstSetIterator.hasNext() && nextSetIterator.hasNext()) {
                    eachSetItemDiff = firstSetIterator.next().toInt() - nextSetIterator.next().toInt()
                    if (eachSetItemDiff != 0) {
                        break
                    }
                }
                return@Comparator eachSetItemDiff
            }
        )
        println("Sorted outside: $newSortedList")
    }
}