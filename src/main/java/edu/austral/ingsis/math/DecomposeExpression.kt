package edu.austral.ingsis.math

class DecomposeExpression() {
    private val integerChars = '0'..'9'
    private val operatingChars = mapOf(
        '+' to {left: Function, right: Function -> Sum(left, right)},
        '-' to {left: Function, right: Function -> Difference(left, right)},
        '*' to {left: Function, right: Function -> Product(left, right)},
        '/' to {left: Function, right: Function -> Division(left, right)},
        '|' to {left: Function, right: Function -> Module(left, right)},
        '^' to {left: Function, right: Function -> Power(left, right)}
    )
    fun decompose(str: String): Function{
        val string = str.replace(" ", "")

        if(hasParenthesisChars(string)){
            return decomposeWParenthesis(string)
        }
        return createFunction(string)
    }

    private fun createFunction(string: String): Function {
        if (!hasOperatingChars(string)) {
            return getNumOrVar(string)
        }
        for ((opp, func) in operatingChars) {
            if (hasDelimiter(string, opp)) {
                return decompose(getParts(string, opp), func)
            }
        }
        return Literal(9.0F)
    }

    private fun decomposeWParenthesis(string: String): Function {
        val parts = getParts(string, '(')
//        chequear de sacar el ultimo caracter operador porque en caso de que sea 3 + (4*5) - 6
        val left: Function?
        val firstOpp: ((Function, Function) -> Function)?
        var firstPart: String
        if(parts[0].isEmpty()){
            left = null
            firstOpp = null
        }
        else{
            firstPart = parts[0].replace("(", "")
            val lastChar = parts[0][parts[0].length-1]
            firstOpp = operatingChars.get(lastChar)
            left = decompose(parts[0].substring(0, parts[0].length-1))
        }
        val right : List<String> = getParts(string, ')')
        if(left == null){
            firstPart = right[0].replace("(", "")
        }
        val right0 = decompose(right[0])
        val right1: Function?
        val lastOpp: ((Function, Function) -> Function)?
        if(right[0].isEmpty()){
            right1 = null
            lastOpp = null
        }
        else{
            val part = if(left == null) right[0].replace("(", "").replace(")", "")
            else right[0].replace(")", "")
            val lastChar = parts[0][0]
            lastOpp = operatingChars.get(lastChar)
            right1 = decompose(parts[0].substring(0, parts[0].length-1))
        }

        return if (lastOpp != null && right1 != null) {

            if (firstOpp != null && left != null) {
                firstOpp(left, lastOpp(right0, right1))
            } else lastOpp(right0, right1)

        } else {

            if (firstOpp != null && left != null) {
                firstOpp(left, right0)
            } else throw Error("the operations character doesn't exist")

        }

    }

    private fun getNumOrVar(string: String): Function {
        return if (isNumeric(string)) Literal(string.toFloat())
        else Variable(string)
    }


    private fun decompose(parts: List<String>, returnType: (Function, Function) -> Function): Function {
        val left: Function = if(parts[0].isNotEmpty()) this.decompose(parts[0])
        else Literal(0.0f)

        val right: Function = if(parts[1].isNotEmpty()) this.decompose(parts[1])
        else Literal(0.0f)

        return returnType(left, right)
    }

    private fun hasDelimiter(string: String, delimiter: Char): Boolean {
        val parts = string.split(delimiter, limit = 2)
        return parts.size == 2
    }

    private fun isNumeric(string: String): Boolean = string.all { it in integerChars }

    private fun hasOperatingChars(s: String): Boolean = s.any { it in operatingChars }
    private fun hasParenthesisChars(s: String): Boolean = s.any { it == '(' }

    private fun getParts(string: String, delimiter: Char): List<String> = string.split(delimiter, limit = 2)
}