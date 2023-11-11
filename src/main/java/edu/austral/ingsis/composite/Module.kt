package edu.austral.ingsis.composite

import kotlin.math.abs

class Module(left: Function, right: Function) : NoTerminalExpression(left, right){
    override fun getString(): String {
        if(right is NoFunction && left is TerminalExpression) return "|$left|"
        else if (left is NoFunction && right is TerminalExpression) return "|$right|"
        return Module(evaluateRight(), evaluateLeft()).getStringWithOut()
    }

    override fun evaluate(): Function {
        if(right is NoFunction && left is Literal){
            return Literal(abs((left as Literal).getNumber()))
        }
        else if(left is NoFunction && right is Literal){
            return Literal(abs((right as Literal).getNumber()))
        }
        return Module(evaluateRight(), evaluateLeft()).evaluate()
        // TODO: MAKE IT WITH VARIABLES
    }
    override fun toString(): String {
        return getString()
    }
    private fun getStringWithOut(): String {
        if(right is NoFunction) return "$left"
        else if (left is NoFunction) return "$right"
        return Module(evaluateRight(), evaluateLeft()).getStringWithOut()
    }
}
