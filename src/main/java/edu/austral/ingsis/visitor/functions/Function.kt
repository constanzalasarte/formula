package edu.austral.ingsis.visitor.functions

import edu.austral.ingsis.visitor.visitors.Visitor

interface Function {
    fun <T> accept(visitor: Visitor<T>): T
}