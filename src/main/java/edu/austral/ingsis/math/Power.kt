package edu.austral.ingsis.math

import kotlin.math.pow


class Power(left: Function, right: Function) : NoTerminalExpression(left, right){
    override fun getString(): String {
        return "$left^$right"
    }

    override fun evaluate(): Function {
        if(right is Literal && left is Literal){
            return Literal((left as Literal).getNumber().pow((right as Literal).getNumber()))
        }
        return Power(evaluateRight(), evaluateLeft()).evaluate()
        return this
        // TODO: MAKE IT WITH VARIABLES
    }

    override fun toString(): String {
        return getString()
    }

}