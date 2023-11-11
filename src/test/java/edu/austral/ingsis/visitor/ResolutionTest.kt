package edu.austral.ingsis.visitor

import edu.austral.ingsis.visitor.functions.*
import edu.austral.ingsis.visitor.visitors.ResolveVisitor
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ResolutionTest {
    private val visitor = ResolveVisitor()
    /**
     * Case 1 + 6
     */
    @Test
    fun shouldResolveSimpleFunction1() {
        val expected = 7.0f
        val result = Sum(Literal(1.0f), Literal(6.0f)).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case 12 / 2
     */
    @Test
    fun shouldResolveSimpleFunction2() {
        val expected = 6.0f
        val result = Division(Literal(12.0f), Literal(2.0f)).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    fun shouldResolveSimpleFunction3() {
        val expected = 13.5f
        val result = Product(Division(Literal(9.0f), Literal(2.0f)), Literal(3.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    fun shouldResolveSimpleFunction4() {
        val expected = 20.25f
        val result = Power(Division(Literal(27.0f), Literal(6.0f)), Literal(2.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case 36 ^ (1/2)
     */
    @Test
    fun shouldResolveSimpleFunction5() {
        val expected = 6.0f
        val result = Power(Literal(36.0f), Division(Literal(1.0f), Literal(2.0f)))
            .accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case |136|
     */
    @Test
    fun shouldResolveSimpleFunction6() {
        val expected = 136.0f
        val result = Module(Literal(136.0f), null).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case |-136|
     */
    @Test
    fun shouldResolveSimpleFunction7() {
        val expected = 136.0f
        val result = Module(Literal(-136.0f), null).accept(visitor)
        assertEquals(expected, result)
    }

    /**
     * Case (5 - 5) * 8
     */
    @Test
    fun shouldResolveSimpleFunction8() {
        val expected = 0.0f
        val result = Product(Difference(Literal(5.0f), Literal(5.0f)), Literal(8.0f))
            .accept(visitor)
        assertEquals(expected, result)
    }
}