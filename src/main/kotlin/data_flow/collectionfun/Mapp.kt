package org.example.data_flow.collectionfun

fun main(){
val letters = 'a'..'z'
    val ascii = mutableListOf<Int>()
    for (i in letters){
        ascii.add(i.toInt())
    }
    println(ascii)
    val ascList = ('A'..'Z').map {
        it.toInt()
    }
    println(ascList)
}