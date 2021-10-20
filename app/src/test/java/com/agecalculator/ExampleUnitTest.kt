package com.agecalculator

import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        val stringsArray = arrayOf("abbcd", "abc", "abcde", "aaab")
//        val measureTime = measureTimeMillis {
//            println("List of common chars: ${findUniqueCommonChars(stringsArray)}")
//        }
//        println("Time taken: $measureTime")

        val measureOptimizedTime = measureTimeMillis {
            println("Optimized list of common chars: ${findUniqueCommonCharsOptimized(stringsArray)}")
        }
        print("Optimized time taken: $measureOptimizedTime")
    }

    private fun findUniqueCommonChars(stringsArray: Array<String>): MutableList<Char> {
        val listOfCommonChars = mutableListOf<Char>()
        for (i in 0 until stringsArray.size) {
            val charArray = stringsArray[i].toCharArray()
            charArray.forEach { char ->
                var charAppearanceCount = 0
                for (j in 0 until stringsArray.size) {
                    if (stringsArray[j].contains(char)) {
                        charAppearanceCount += 1
                    }
                }

                if (charAppearanceCount == stringsArray.size &&
                        !listOfCommonChars.contains(char)) {
                    listOfCommonChars.add(char)
                }
            }
        }
        return listOfCommonChars
    }

    private fun findUniqueCommonCharsOptimized(stringsArray: Array<String>): MutableList<Char> {
        val listOfCommonChars = mutableListOf<Char>()
        for (i in 0 until stringsArray.size) {
            val charArray = stringsArray[i].toCharArray()
            charArrayLoop@ for (c in 0 until charArray.size) {
                val char = charArray[c]
                if (listOfCommonChars.contains(char)) {
                    println("list already contains: $char")
                    continue@charArrayLoop
                }
                var charAppearanceCount = 0
                for (j in 0 until stringsArray.size) {
                    if (stringsArray[j].contains(char)) {
                        charAppearanceCount += 1
                    } else {
                        println("before continue: $char")
                        continue@charArrayLoop
                    }
                }
                println("out of for loop: $char")
                if (charAppearanceCount == stringsArray.size) {
                    listOfCommonChars.add(char)
                }
            }
        }
        return listOfCommonChars
    }
}