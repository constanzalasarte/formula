package edu.austral.ingsis.math

class Sum(left: Function, right: Function) : NoTerminalExpression(left, right) {

    override fun evaluate(): Function {
        if(right is Literal && left is Literal){
            return Literal((left as Literal).getNumber() + (right as Literal).getNumber())
        }
        return Sum(evaluateRight(), evaluateLeft()).evaluate()
        // TODO: MAKE IT WITH VARIABLES
    }

    override fun getString(): String {
        return "$left + $right"
    }

    override fun toString(): String {
        return getString()
    }
}