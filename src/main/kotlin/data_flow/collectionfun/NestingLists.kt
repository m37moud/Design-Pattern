package org.example.data_flow.collectionfun

fun main (){
    val listOFLists = listOf(listOf(1,2), listOf(3,4,5), listOf(6,7,8,9))
    val flatList = mutableListOf<Int>()
    for (list in listOFLists){
        flatList.addAll(list)
    }
    println(flatList)
    /**
     * it takes each list in lists and make it one list containing all elements
     */
    val newFlatList = listOFLists.flatMap { it }
    val newFlatList2 = listOFLists.flatten()
    println(newFlatList)
}