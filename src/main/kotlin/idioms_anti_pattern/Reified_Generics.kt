package org.example.idioms_anti_pattern

import kotlin.reflect.KClass

fun main() {
    printIfTheSameType(Int::class, 1)
    printIfTheSameType(Int::class, 2L)
    printIfTheSameType(Long::class, 3L)
    //refiend
    printIfTheSameType<Int>(1)
    printIfTheSameType<Int>(2L)
    printIfTheSameType<Long>(3L)


    printList(listOf(1, 2, 3))
    printList(listOf(1L, 2L, 3L))

}

/**
 * this function cant compile cant check for instance of erased type T
 */
//fun <T> printIfTheSameType(a: Number) {
//    if (a is T) {
//        println(a)
//    }
//}

fun <T : Number> printIfTheSameType(clazz: KClass<T>, a: Number) {
    if (clazz.isInstance(a)) {
        println("yes")
    } else {
        println("no")
    }
}

inline fun <reified T : Number> printIfTheSameType(a: Number) {
    if (a is T) {
        println("yes")
    } else {
        println("no")
    }
}

//fun printList(list: List<Int>) {
//    println("This is a list of Ints")
//    println(list)
//}
//
//fun printList(list: List<Long>) {
//    println("This is a list of Longs")
//    println(list)
//}

inline fun <reified T : Any> printList(list: List<T>) {
    when {
        1 is T -> println("This is a list of Ints")
        1L is T -> println("This is a list of Longs")
        else -> println("This is a list of else")
    }
    println(list)
}