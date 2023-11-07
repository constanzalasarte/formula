package edu.austral.ingsis.math

interface Function {
    fun getString(): String
    fun evaluate(): Function
    fun getVariablesList(): List<String>
}