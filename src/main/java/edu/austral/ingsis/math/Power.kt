package edu.austral.ingsis.math

import kotlin.math.pow


class Power(left: Function, right: Function) : NoTerminalExpression(left, right){
    override fun getString(): String {
        return "$left ^ $right"
    }

    override fun evaluate(): Function {
        if(right is Literal && left is Literal){
            return Literal((left as Literal).getNumber().pow((right as Literal).getNumber()))
        }
        else if( left is NoTerminalExpression && right is TerminalExpression){
            val result = left.evaluate()
            if(result is Literal){
                return Literal(result.getNumber().pow((right as Literal).getNumber()))
            }
        }
        else if( left is TerminalExpression && right is NoTerminalExpression){
            val result = right.evaluate()
            if(result is Literal){
                return Literal((left as Literal).getNumber().pow(result.getNumber()))
            }
        }
        return Power(evaluateRight(), evaluateLeft()).evaluate()
    }

    override fun toString(): String {
        return getString()
    }

}