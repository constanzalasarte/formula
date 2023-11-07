package edu.austral.ingsis.math

import java.lang.Module
import kotlin.math.abs

class Module(left: Function, right: Function) : NoTerminalExpression(left, right){
    override fun getString(): String {
        if(left is NoFunction) return "|$left|"
        else if (right is NoFunction) return "|$right|"
        return "|$left$right|"
    }

    override fun evaluate(): Function {
        if(right is Literal && left is Literal){
            return Literal(abs((left as Literal).getNumber()))
        }
        return Module(evaluateRight(), evaluateLeft()).evaluate()
        return this
        // TODO: MAKE IT WITH VARIABLES
    }
    override fun toString(): String {
        return getString()
    }

}