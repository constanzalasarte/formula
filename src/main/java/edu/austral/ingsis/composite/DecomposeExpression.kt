package edu.austral.ingsis.composite

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
        val string = replaceVariables(str).replace(" ", "")

        if(hasParenthesisChars(string)){
            return decomposeWParenthesis(string)
        }
        return createFunction(string)
    }

    private fun replaceVariables(string: String): String {
        var string1 = string.replace(" ", "").replace("where", " ").replace("and", " ")
        val map = HashMap<String, String>()
        while (hasEqualsParameter(string1)) {
            val equalsIndex = string1.indexOf("=")
            val variable = getVariable(string1, equalsIndex)
            val number = getNumber(string1, equalsIndex)
            map.put(variable, number)
            string1 = updateString(string1, equalsIndex)
        }
        for ((key, value) in map) {
            string1 = string1.replace(key, value)
        }
        return string1
    }

    private fun updateString(str: String, equalsIndex: Int): String {
        val startIndex = str.indexOf(" ")
        val endIndex = if(str.indexOf(" ", equalsIndex) == -1) str.length
        else str.indexOf(" ", equalsIndex)
        return str.removeRange(startIndex.rangeTo(endIndex-1))
    }

    private fun getNumber(string1: String, equalsIndex: Int): String {
        val endIndex = if(string1.indexOf(" ", startIndex = equalsIndex) == -1) string1.length
        else string1.indexOf(" ", startIndex = equalsIndex)
        return string1.substring(equalsIndex+1, endIndex)
    }

    private fun getVariable(string1: String, equalsIndex: Int): String {
        val startIndex = string1.indexOf(" ")
        return string1.substring(startIndex+1, equalsIndex)
    }

    private fun createFunction(string: String): Function {
        if (!hasOperatingChars(string)) {
            return getNumOrVar(string)
        }
        if(hasDelimiter(string, '|')){
            val index = string.indexOf('|')
            val str = string.filterIndexed{ i, _-> i != index}
            return decomposeWPipe(getParts(str, '|'), operatingChars.get('|')!!)
        }
        for ((opp, func) in operatingChars) {

            if (hasDelimiter(string, opp)) {
                return decompose(getParts(string, opp), func)
            }
        }
        return Literal(9.0F)
    }

    private fun decomposeWPipe(parts: List<String>, get: (Function, Function) -> NoTerminalExpression): Function {
        val left: Function = if(parts[0].isNotEmpty()) this.decompose(parts[0])
        else NoFunction()
        val right: Function?
        val lastOpp: ((Function, Function) -> Function)?
        if(parts[1].isEmpty()){
            right = null
            lastOpp = null
        }
        else{
            val part = parts[1].replace(")", "")
            val lastChar = part[0]
            lastOpp = operatingChars.get(lastChar)
            right = decompose(part.substring(1))
        }
        return if (lastOpp != null && right != null) {
            lastOpp(get(left, NoFunction()), right)
        } else {
            throw Error("the operations character doesn't exist")
        }
    }

    private fun decomposeWParenthesis(string: String): Function {
        val parts = getParts(string, '(')
        val left: Function?
        val firstOpp: ((Function, Function) -> Function)?
        var firstPart: String
        if(parts[0].isEmpty()){
            left = null
            firstOpp = null
        }
        else{
            firstPart = parts[0].replace("(", "")
            val lastChar = firstPart[parts[0].length-1]
            firstOpp = operatingChars.get(lastChar)
            left = decompose(firstPart.substring(0, parts[0].length-1))
        }

        val right : List<String> = getParts(parts[1], ')')

        val right0: Function = if(left == null){
            firstPart = right[0].replace("(", "")
            decompose(firstPart)
        } else{ decompose(right[0]) }

        val right1: Function?
        val lastOpp: ((Function, Function) -> Function)?
        if(right[1].isEmpty()){
            right1 = null
            lastOpp = null
        }
        else{
            val part = right[1].replace(")", "")
            val lastChar = part[0]
            lastOpp = operatingChars.get(lastChar)
            right1 = decompose(part.substring(1))
        }

        return if (lastOpp != null && right1 != null) {

            if (firstOpp != null && left != null) {
                firstOpp(left, lastOpp(right0, right1))
            } else lastOpp(right0, right1)

        } else {

            if (firstOpp != null && left != null) {
                firstOpp(left, right0.evaluate())
            } else throw Error("the operations character doesn't exist")

        }

    }

    private fun getNumOrVar(string: String): Function {
        return if (isNumeric(string)) Literal(string.toFloat())
        else Variable(string)
    }


    private fun decompose(parts: List<String>, returnType: (Function, Function) -> Function): Function {
        val left: Function = if(parts[0].isNotEmpty()) this.decompose(parts[0])
        else NoFunction()

        val right: Function = if(parts[1].isNotEmpty()) this.decompose(parts[1])
        else NoFunction()

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
    private fun hasEqualsParameter(string: String): Boolean = string.any { it == '=' }
}