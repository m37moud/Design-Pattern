package org.example.idioms_anti_pattern.scope

/**
 * run look like let function but it use this of the context
 * of the function instead of using it
 * note we can use omitted this
 */

fun main() {
    val myName = "mahmoud"
    myName.run {

        println(this.length) // omitted this

        println(length)
    }

    /**
     * run it usefully like apply to init the object, but instead it returns the object
     * run return the function which happened on the object
     */
    val lowerCaseName = JamesBond().run {
        name = "ROGER MOORE"
        movie = "THE MAN WITH THE GOLDEN GUN"
        name.toLowerCase() // <= Not JamesBond type
    }
    println(lowerCaseName)
}



