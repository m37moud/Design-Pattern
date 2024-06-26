package org.example.data_flow.collectionfun

fun main() {
    val numbers = 1..100

    val notFizzbuzz = mutableListOf<Int>()

    for (n in numbers) {
        if (n % 3 == 0 || n % 5 == 0) {
            notFizzbuzz.add(n)
        }
    }

    println(notFizzbuzz)

    val notFizzBuzz = (1..100).filter {
        it % 3 == 0 || it % 5 == 0
    }
    println(notFizzBuzz)
}