package org.example.data_flow.collectionfun

fun main() {
    val numbers = 1..100
    println(addListOfNumbers(numbers.toList()))
    /**
     * it take to argument first argument and add each number from left to right
     */
    val sum = numbers.reduce { sum, n ->
        sum + n
    }
    println(sum)
}

fun addListOfNumbers(numbers: List<Int>): Int {
    var sum = 0
    for (n in numbers) {
        sum += n
    }
    return sum
}