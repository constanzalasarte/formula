package edu.austral.ingsis.visitor.functions

import edu.austral.ingsis.visitor.visitors.Visitor

class Literal(val number: Float): Function {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visit(this)
    }

}