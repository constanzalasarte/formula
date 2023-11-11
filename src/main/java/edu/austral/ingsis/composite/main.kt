package edu.austral.ingsis.composite

fun main(){
    val decomposeExpression = DecomposeExpression()
    val func = decomposeExpression.decompose("1+6")
    println(func.getString())
    val func1 = decomposeExpression.decompose("1-6")
    println(func1.getString())
    val func2 = decomposeExpression.decompose("x-6")
    println(func2.getString())
}