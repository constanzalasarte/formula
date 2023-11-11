package edu.austral.ingsis.math

class NoFunction: Function {
    override fun getString(): String {
        return ""
    }

    override fun evaluate(): Function {
        return this
    }

    override fun getVariablesList(): List<String> {
        return emptyList()
    }
}