package org.example.idioms_anti_pattern.scope

/**
 * WITH() function not extension function
 * it like run and let functions you can return any result from with()
 * but it take object as argument to make functions
 */
fun main() {
    with(JamesBond()) {
        name = "Pierce Brosnan"

        println(this.name)
    }
}