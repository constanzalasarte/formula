package edu.austral.ingsis.math

class Literal(private val number: Float) : Function {
    override fun getString(): String {
        if(isFloatAnInt(number)) return number.toInt().toString()
        return toString()
    }

    override fun evaluate(): Function {
        return this
    }

    override fun getVariablesList(): List<String> {
        return emptyList()
    }

    fun getNumber(): Float{
        return number
    }

    override fun toString(): String {
        return number.toString()
    }

    private fun isFloatAnInt(floatValue: Float): Boolean {
        return floatValue == floatValue.toInt().toFloat()
    }

}