package edu.austral.ingsis.visitor

import edu.austral.ingsis.visitor.functions.*
import edu.austral.ingsis.visitor.visitors.GetVariablesListVisitor
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ListVariablesTest {
    private val visitor = GetVariablesListVisitor()

    /**
     * Case 1 + 6
     */
    @Test
    fun shouldListVariablesFunction1() {
        val expected = emptyList<String>()
        val result = Sum(Literal(1.0f), Literal(6.0f)).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case 12 / div
     */
    @Test
    fun shouldListVariablesFunction2() {
        val expected = listOf("div")
        val result = Division(Literal(12.0f), Variable("div")).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    fun shouldListVariablesFunction3() {
        val expected = listOf("x", "y")
        val result = Product(Division(Literal(9.0f), Variable("x")), Variable("y"))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    fun shouldListVariablesFunction4() {
        val expected = listOf("a", "b")
        val result = Power(Division(Literal(27.0f), Variable("a")), Variable("b"))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    fun shouldListVariablesFunction5() {
        val expected = listOf("z")
        val result = Power(Variable("z"), Division(Literal(1.0f), Literal(2.0f)))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldListVariablesFunction6() {
        val expected= listOf("value")
        val result = Difference(Module(Variable("value"), null), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldListVariablesFunction7() {
        val expected= listOf("value")
        val result = Difference(Module(Variable("value"), null), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    fun shouldListVariablesFunction8() {
        val expected = listOf("i")
        val result = Product(Difference(Literal(5.0f), Variable("i")), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }
}