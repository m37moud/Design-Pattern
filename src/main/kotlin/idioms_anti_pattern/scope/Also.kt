package org.example.idioms_anti_pattern.scope

fun main() {
    val l = (1..100).toList()

    l.filter { it % 2 == 0 }
        // Prints, but doesn't mutate or effect on the collection
        .also { println(it) }
        .map { it * it }
    /**
     * we can shorten the code by using also
     * it make the code more cleaning and readable
     *
     */
    multiTwoNum(4, 6).also { println(it) }
    multiTwoIntAndPrint(4, 6)
}


fun multiTwoIntAndPrint(a: Int, b: Int) {
    val result = a * b
    println(result)

}
fun multiTwoNum(x: Int, y: Int) = x * y
