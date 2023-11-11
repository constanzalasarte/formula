package edu.austral.ingsis.composite

class Literal(private val number: Float) : TerminalExpression() {
    override fun getString(): String {
        if(isFloatAnInt(number)) return number.toInt().toString()
        return number.toString()
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
        return getString()

    }

    private fun isFloatAnInt(floatValue: Float): Boolean {
        return floatValue == floatValue.toInt().toFloat()
    }

}