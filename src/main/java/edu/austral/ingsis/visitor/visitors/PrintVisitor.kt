package edu.austral.ingsis.visitor.visitors

import edu.austral.ingsis.visitor.functions.Module
import edu.austral.ingsis.visitor.functions.*

class PrintVisitor : Visitor<String> {
    override fun visit(sum: Sum): String {
        return "${sum.left.accept(this)} + ${sum.right.accept(this)}"
    }

    override fun visit(difference: Difference): String {
        return "${difference.left.accept(this)} - ${difference.right.accept(this)}"
    }

    override fun visit(product: Product): String {
        return "${product.left.accept(this)} * ${product.right.accept(this)}"
    }

    override fun visit(division: Division): String {
        return "${division.left.accept(this)} / ${division.right.accept(this)}"
    }

    override fun visit(module: Module): String {
        if(module.left == null) return "|${module.right!!.accept(this)}|"
        else if(module.right == null) return "|${module.left.accept(this)}|"
        return "|${module.left.accept(this)} ${module.right.accept(this)}|"
    }

    override fun visit(power: Power): String {
        return "${power.left.accept(this)} ^ ${power.right.accept(this)}"
    }

    override fun visit(variable: Variable): String {
        return variable.reference
    }

    override fun visit(literal: Literal): String {
        if(isFloatAnInt(literal.number)) return literal.number.toInt().toString()
        return literal.number.toString()
    }

    private fun isFloatAnInt(floatValue: Float): Boolean {
        return floatValue == floatValue.toInt().toFloat()
    }


}