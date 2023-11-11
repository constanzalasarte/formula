package edu.austral.ingsis.visitor.functions

import edu.austral.ingsis.visitor.visitors.Visitor

class Module(val left: Function?, val right: Function?): Function {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visit(this)
    }

}