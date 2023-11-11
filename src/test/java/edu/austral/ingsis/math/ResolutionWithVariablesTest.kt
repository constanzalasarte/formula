package edu.austral.ingsis.math

import junit.framework.TestCase
import org.junit.Test

class ResolutionWithVariablesTest {
    private val decomposeExpression = DecomposeExpression()

    /**
     * Case 1 + x where x = 3
     */
    @Test
    fun shouldResolveFunction1() {
        val expected = 4.0f
        val result = decomposeExpression.decompose("1 + x where x = 3").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    fun shouldResolveFunction2() {
        val expected = 3.0f
        val result = decomposeExpression.decompose("12 / div where div = 4").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    fun shouldResolveFunction3() {
        val expected = 12.0f
        val result = decomposeExpression.decompose("(9 / x) * y where x = 3 and y = 4").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    fun shouldResolveFunction4() {
        val expected = 27.0f
        val result = decomposeExpression.decompose("(27 / a) ^ b where a = 9 and b = 3").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    fun shouldResolveFunction5() {
        val expected = 6.0f
        val result = decomposeExpression.decompose("z ^ (1/2) where z = 36").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    fun shouldResolveFunction6() {
        val expected = 0.0f
        val result = decomposeExpression.decompose("|value| - 8 where value = 8").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    fun shouldResolveFunction7() {
        val expected = 0.0f
        val result = decomposeExpression.decompose("|value| - 8 where value = 8").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    fun shouldResolveFunction8() {
        val expected = 24.0f
        val result = decomposeExpression.decompose("(5 - i) * 8 where i = 2").evaluate()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }
}