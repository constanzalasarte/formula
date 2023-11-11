package edu.austral.ingsis.visitor

import edu.austral.ingsis.visitor.visitors.PrintVisitor
import edu.austral.ingsis.visitor.functions.*
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PrintTest {
    private val visitor = PrintVisitor()
    /**
     * Case 1 + 6
     */
    @Test
    fun shouldPrintFunction1() {
        val expected = "1 + 6"
        val result = Sum(Literal(1.0f), Literal(6.0f)).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case 12 / 2
     */
    @Test
    fun shouldPrintFunction2() {
        val expected = "12 / 2"
        val result = Division(Literal(12.0f), Literal(2.0f)).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    fun shouldPrintFunction3() {
        val expected = "9 / 2 * 3"
        val result = Product(Division(Literal(9.0f), Literal(2.0f)), Literal(3.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    fun shouldPrintFunction4() {
        val expected = "27 / 6 ^ 2"
        val result = Power(Division(Literal(27.0f), Literal(6.0f)), Literal(2.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldPrintFunction6() {
        val expected = "|value| - 8"
        val result = Difference(Module(Variable("value"), null), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case |value| - 8
     */
    @Test
    fun shouldPrintFunction7() {
        val expected = "|value| - 8"
        val result = Difference(Module(Variable("value"), null), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    fun shouldPrintFunction8() {
        val expected = "5 - i * 8"
        val result = Product(Difference(Literal(5.0f), Variable("i")), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }
}