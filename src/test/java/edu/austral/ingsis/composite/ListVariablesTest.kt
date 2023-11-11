package edu.austral.ingsis.composite

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ListVariablesTest {
    private val decomposeExpression = DecomposeExpression()

    /**
     * Case 1 + 6
     */
    @Test
    fun shouldListVariablesFunction1() {
        val result = emptyList<String>()
        val function = decomposeExpression.decompose("1 + 6")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case 12 / div
     */
    @Test
    fun shouldListVariablesFunction2() {
        val result = listOf("div")
        val function = decomposeExpression.decompose("12 / div")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    fun shouldListVariablesFunction3() {
        val result = listOf("x", "y")
        val function = decomposeExpression.decompose("(9 / x) * y")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    fun shouldListVariablesFunction4() {
        val result = listOf("a", "b")
        val function = decomposeExpression.decompose("(27 / a) ^ b")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    fun shouldListVariablesFunction5() {
        val result = listOf("z")
        val function = decomposeExpression.decompose("z ^ (1/2)")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldListVariablesFunction6() {
        val result = listOf("value")
        val function = decomposeExpression.decompose("|value| - 8")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldListVariablesFunction7() {
        val result = listOf("value")
        val function = decomposeExpression.decompose("|value| - 8")
        assertEquals(result, function.getVariablesList())
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    fun shouldListVariablesFunction8() {
        val result = listOf("i")
        val function = decomposeExpression.decompose("(5 - i) * 8")
        assertEquals(result, function.getVariablesList())
    }
}