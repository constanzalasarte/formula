package edu.austral.ingsis.math

import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class ResolutionTest {
    private val decomposeExpression = DecomposeExpression()
    /**
     * Case 1 + 6
     */
    @Test
    fun shouldResolveSimpleFunction1() {
        val expected = 7.0f
        val result = decomposeExpression.decompose("1 + 6").evaluate()
        assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case 12 / 2
     */
    @Test
    fun shouldResolveSimpleFunction2() {
        val expected = 6.0f
        val result = decomposeExpression.decompose("12 / 2").evaluate()
        assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    fun shouldResolveSimpleFunction3() {
        val expected = 13.5f
        val result = decomposeExpression.decompose("(9 / 2) * 3").evaluate()
        assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    fun shouldResolveSimpleFunction4() {
        val expected = 20.25f
        val result = decomposeExpression.decompose("(27 / 6) ^ 2").evaluate()
        assertEquals(expected, result.toString().toFloat())
    }

    /**
     * Case 36 ^ (1/2)
     */
    @Test
    fun shouldResolveSimpleFunction5() {
        val result = 6.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(6.0))
    }

    /**
     * Case |136|
     */
    @Test
    fun shouldResolveSimpleFunction6() {
        val result = 136.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(136.0))
    }

    /**
     * Case |-136|
     */
    @Test
    fun shouldResolveSimpleFunction7() {
        val result = 136.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(136.0))
    }

    /**
     * Case (5 - 5) * 8
     */
    @Test
    fun shouldResolveSimpleFunction8() {
        val result = 0.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(0.0))
    }
}