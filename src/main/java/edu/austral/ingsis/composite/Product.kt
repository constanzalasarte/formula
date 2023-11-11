package edu.austral.ingsis.composite

class Product(left: Function, right: Function): NoTerminalExpression(left, right) {
    override fun getString(): String {
        return "$left * $right"
    }

    override fun evaluate(): Function {
        if(right is Literal && left is Literal){
            return Literal((left as Literal).getNumber() * (right as Literal).getNumber())
        }
        return Product(evaluateRight(), evaluateLeft()).evaluate()
    }

    override fun toString(): String {
        return getString()
    }
}