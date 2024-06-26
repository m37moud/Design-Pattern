package org.example.data_flow.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * flow have two section
 * 1.subscribe() : to make subscribe or consumer waiting to receive the message
 * 2.publish() : produce send the message
 * this two section in flow
 * publish() replace by emit() and subscribe() replaced by collect()
 */

/**
 * deference between cold and hot stream
 * cold stream: mean if no subscribed flow doesn't run , every new subscribe will listen to events from start
 * Hot Stream: like sequence it runs whenever no subscribe , and subscribe start listening from the time which did subscribe
 */
fun main() {
    runBlocking {
        val flowNumbers = flow<Int> {
            (1..10).forEach {
                println("sending $it")
                emit(it)
                if (it == 9) {
                    throw RuntimeException()
                }
            }
        }
        (1..4).forEach { coroutineId ->
            delay(5000)
            launch(Dispatchers.Default) {
                try {
                    flowNumbers.collect { number ->
                        delay(1000)
                        println("Coroutine $coroutineId received $number")
                    }
                } catch (e: Exception) {
                    println("Coroutine $coroutineId got an error")
                }
            }
        }
    }
    }



