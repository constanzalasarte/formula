package edu.austral.ingsis.math

abstract class NoTerminalExpression(var left: Function, var right: Function) : Function{
    private val integerChars = '0'..'9'
    private val operatingChars = arrayOf('+', '-', '*', '/', '|', '^')
    fun evaluateRight(): Function{
        return right.evaluate()
    }

    fun evaluateLeft(): Function{
        return left.evaluate()
    }
    override fun getVariablesList(): List<String> {
        val str = this.getString().filter { it != ' ' && it != '(' && it != ')' && it != '.'}
        val pattern = operatingChars.joinToString("|") { Regex.escape(it.toString()) }
        val regex = Regex("[$pattern]")
        val result = str.split(regex)
        return result.flatMap { substr ->
            substr.split(Regex("[" + integerChars.joinToString("|") { Regex.escape(it.toString()) } + "]"))
        }.filter { it != "" }

    }

}