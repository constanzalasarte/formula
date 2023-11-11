package edu.austral.ingsis.composite


class Difference(left: Function, right: Function): NoTerminalExpression(left, right) {
    override fun evaluate(): Function {
        if(right is Literal && left is Literal){
            return Literal((left as Literal).getNumber() - (right as Literal).getNumber())
        }
        return Difference(evaluateRight(), evaluateLeft()).evaluate()
        // TODO: MAKE IT WITH VARIABLES
    }

    override fun getString(): String {
        return "$left - $right"

    }

    override fun toString(): String {
        return getString()
    }
}