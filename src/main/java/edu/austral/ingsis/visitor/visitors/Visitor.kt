package edu.austral.ingsis.visitor.visitors

import edu.austral.ingsis.visitor.functions.*


interface Visitor<T> {
    fun visit(sum: Sum): T
    fun visit(difference: Difference): T
    fun visit(product: Product): T
    fun visit(division: Division): T
    fun visit(module: Module): T
    fun visit(power: Power): T
    fun visit(variable: Variable): T
    fun visit(literal: Literal): T
}