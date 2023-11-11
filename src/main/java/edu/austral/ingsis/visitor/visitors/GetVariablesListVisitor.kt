package edu.austral.ingsis.visitor.visitors

import edu.austral.ingsis.visitor.functions.*

class GetVariablesListVisitor:Visitor<List<String>> {
    override fun visit(sum: Sum): List<String> {
        return sum.left.accept(this) + sum.right.accept(this)
    }

    override fun visit(difference: Difference): List<String> {
        return difference.left.accept(this) + difference.right.accept(this)
    }

    override fun visit(product: Product): List<String> {
        return product.left.accept(this) + product.right.accept(this)
    }

    override fun visit(division: Division): List<String> {
        return division.left.accept(this) + division.right.accept(this)
    }

    override fun visit(module: Module): List<String> {
        if(module.left == null) return module.right!!.accept(this)
        else if(module.right == null) return  module.left.accept(this)
        return module.left.accept(this) + module.right.accept(this)
    }

    override fun visit(power: Power): List<String> {
        return power.left.accept(this) + power.right.accept(this)
    }

    override fun visit(variable: Variable): List<String> {
        return listOf(variable.reference)
    }

    override fun visit(literal: Literal): List<String> {
        return emptyList()
    }
}