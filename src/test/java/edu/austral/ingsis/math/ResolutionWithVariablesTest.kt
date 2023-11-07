package edu.austral.ingsis.math

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class ResolutionWithVariablesTest {
    /**
     * Case 1 + x where x = 3
     */
    @Test
    fun shouldResolveFunction1() {
        val result = 4.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(4.0))
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    fun shouldResolveFunction2() {
        val result = 3.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(3.0))
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    fun shouldResolveFunction3() {
        val result = 12.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(12.0))
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    fun shouldResolveFunction4() {
        val result = 27.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(27.0))
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    fun shouldResolveFunction5() {
        val result = 6.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(6.0))
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    fun shouldResolveFunction6() {
        val result = 0.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(0.0))
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    fun shouldResolveFunction7() {
        val result = 0.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(0.0))
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    fun shouldResolveFunction8() {
        val result = 24.0
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(24.0))
    }
}