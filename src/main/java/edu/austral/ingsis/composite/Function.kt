package edu.austral.ingsis.composite

interface Function {
    fun getString(): String
    fun evaluate(): Function
    fun getVariablesList(): List<String>
}