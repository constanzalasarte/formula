package edu.austral.ingsis.math

import junit.framework.TestCase.assertEquals
import org.junit.Test

class PrintTest {
    private val decomposeExpression = DecomposeExpression()
    /**
     * Case 1 + 6
     */
    @Test
    fun shouldPrintFunction1() {
        val expected = "1 + 6"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }

    /**
     * Case 12 / 2
     */
    @Test
    fun shouldPrintFunction2() {
        val expected = "12 / 2"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    fun shouldPrintFunction3() {
        val expected = "9 / 2 * 3"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    fun shouldPrintFunction4() {
        val expected = "27 / 6 ^ 2"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldPrintFunction6() {
        val expected = "|value| - 8"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldPrintFunction7() {
        val expected = "|value| - 8"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    fun shouldPrintFunction8() {
        val expected = "5 - i * 8"
        val function = decomposeExpression.decompose(expected)
        assertEquals(expected, function.getString())
    }
}