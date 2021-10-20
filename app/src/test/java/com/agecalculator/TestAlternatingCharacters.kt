package com.agecalculator

import org.junit.Test
import java.util.regex.Pattern

class TestAlternatingCharacters {
    @Test
    fun alternatingCharacters() {
        println("Characters to remove for AAAAAA: ${alternatingCharacters("AAAAAA")}")
    }

    fun alternatingCharacters(s: String): Int {
        // Write your code here
        val charArray = s.toCharArray()
        var charToCompareNext = s[0]
        var charactersToRemove = 0
        for (i in 1 until charArray.size) {
            if (charToCompareNext == s[i]) {
                charactersToRemove += 1
            } else {
                charToCompareNext = s[i]
            }
        }
        return  charactersToRemove
    }

    fun patternMatcher(s: String): Int {
        val pattern = Pattern.compile("(010)")
        val matcher = pattern.matcher(s)
        var count = 0
        while(matcher.find()) {
            count += 1
        }
        return count
    }

    @Test
    fun iterateOverArray() {
        val arrayOfInt = arrayOf(99, 100, 55, 77)
        for (i in arrayOfInt.indices step 1) {
            println(i)
        }
    }
}