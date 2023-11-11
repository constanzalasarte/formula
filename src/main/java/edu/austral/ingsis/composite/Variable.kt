package edu.austral.ingsis.composite

class Variable(private val reference: String) : TerminalExpression() {
    override fun getString(): String {
        return toString()
    }

    override fun evaluate(): Function {
        return this
    }

    override fun getVariablesList(): List<String> {
        return listOf(reference)
    }

    override fun toString(): String {
        return reference
    }

}