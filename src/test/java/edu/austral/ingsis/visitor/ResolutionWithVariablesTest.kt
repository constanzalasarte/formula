package edu.austral.ingsis.visitor

import edu.austral.ingsis.visitor.functions.*
import edu.austral.ingsis.visitor.visitors.ResolveVisitor
import junit.framework.TestCase
import org.junit.Test
import java.util.Dictionary

class ResolutionWithVariablesTest {
    private val visitor = ResolveVisitor()

    /**
     * Case 1 + x where x = 3
     */
    @Test
    fun shouldResolveFunction1() {
        val expected = 4.0f
        val map = mapOf(Variable("x") to Literal(3.0f))
        visitor.withMap(map);
        val result = Sum(Literal(1.0f), Variable("x")).accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    fun shouldResolveFunction2() {
        val expected = 3.0f
        val map = mapOf(Variable("div") to Literal(4.0f))
        visitor.withMap(map);
        val result = Division(Literal(12.0f), Variable("div")).accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    fun shouldResolveFunction3() {
        val expected = 12.0f
        val map = mapOf(Variable("x") to Literal(3.0f), Variable("y") to Literal(4.0f))
        visitor.withMap(map);
        val result = Product(Division(Literal(9.0f),Variable("x")), Variable("y")).accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    fun shouldResolveFunction4() {
        val expected = 27.0f
        val map = mapOf(Variable("a") to Literal(9.0f), Variable("b") to Literal(3.0f))
        visitor.withMap(map);
        val result = Power(Division(Literal(27.0f), Variable("a")), Variable("b"))
            .accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    fun shouldResolveFunction5() {
        val expected = 6.0f
        val map = mapOf(Variable("z") to Literal(36.0f))
        visitor.withMap(map);
        val result = Power(Variable("z"), Division(Literal(1.0f), Literal(2.0f)))
            .accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    fun shouldResolveFunction6() {
        val expected = 0.0f
        val map = mapOf(Variable("value") to Literal(8.0f))
        visitor.withMap(map)
        val result = Difference(Module(Variable("value"), null), Literal(8.0f))
            .accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    fun shouldResolveFunction7() {
        val expected = 0.0f
        val map = mapOf(Variable("value") to Literal(8.0f))
        visitor.withMap(map)
        val result = Difference(Module(Variable("value"), null), Literal(8.0f))
            .accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result)
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    fun shouldResolveFunction8() {
        val expected = 24.0f
        val map = mapOf(Variable("i") to Literal(2.0f))
        visitor.withMap(map)
        val result = Product(Difference(Literal(5.0f), Variable("i")), Literal(8.0f))
            .accept(visitor)
        visitor.emptyMap()
        TestCase.assertEquals(expected, result.toString().toFloat())
    }
}