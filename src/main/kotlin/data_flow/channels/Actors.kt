package org.example.data_flow.channels

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.runBlocking

fun main() {


    runBlocking {
        val act = actor<Int> {
            channel.consumeEach {
                println(it)
            }
        }
        for (i in 1..10) {
            act.send(i)
        }
    }
}
