package edu.austral.ingsis.visitor.visitors

import edu.austral.ingsis.visitor.functions.*
import kotlin.math.abs
import kotlin.math.pow

class ResolveVisitor: Visitor<Float> {
    private var map: Map<Variable, Literal> = kotlin.collections.emptyMap()
    override fun visit(sum: Sum): Float {
        return sum.left.accept(this) + sum.right.accept(this)
    }

    override fun visit(function: Difference): Float {
        return function.left.accept(this) - function.right.accept(this)
    }

    override fun visit(function: Product): Float {
        return function.left.accept(this) * function.right.accept(this)
    }

    override fun visit(function: Division): Float {
        return function.left.accept(this) / function.right.accept(this)
    }

    override fun visit(module: Module): Float {
        if(module.left == null) return abs(module.right!!.accept(this))
        else if(module.right == null) return abs(module.left.accept(this))
        return abs(module.right.accept(this))
    }

    override fun visit(function: Power): Float {
        return function.left.accept(this).pow(function.right.accept(this))
    }

    override fun visit(variable: Variable): Float {
        for ((key, value) in map){
            if( key.reference == variable.reference) return value.accept(this)
        }
        return 1.0f
    }

    override fun visit(literal: Literal): Float {
        return literal.number
    }

    fun withMap(newMap: Map<Variable, Literal>) {
        map = newMap
    }
    fun emptyMap(){
        map = kotlin.collections.emptyMap()
    }
}