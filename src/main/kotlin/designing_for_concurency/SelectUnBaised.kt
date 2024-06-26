package org.example.designing_for_concurency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.selects.selectUnbiased

/**
 * it like select clause but the difference between select and selectUnbaised
 * select if we have more than one producer and its results come at the same time
 * it pick the first result for the first producer
 * but selectUnbaised : if the same scenario happened it get result randomly
 */
fun main() {
    runBlocking {
        val firstOption = fastProducer("Quick&Angry 7")
        val secondOption = fastProducer("Revengers: Penultimatum")

        delay(10)
        val movieToWatch = selectUnbiased<String> {
            firstOption.onReceive { it }
            secondOption.onReceive { it }
        }
        println(movieToWatch)
    }
}

fun CoroutineScope.fastProducer(
    movieName: String
) = produce(capacity = 1) {
    send(movieName)
}