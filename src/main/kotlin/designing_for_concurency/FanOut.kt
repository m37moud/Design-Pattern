package org.example.designing_for_concurency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.List

/**
 * this pattern help to divide heavy work that takes much time to small pieces
 *
 */
fun main() {
    runBlocking {
        val workChannel = generateWork()

        /**
         * create multiple workers that distribute the work between themselves,
         * no two workers receive the same message, and the messages are not being printed in the order they were sent.
         */
        val workers = List(10) { id ->
            doWork(id, workChannel)
        }
    }
}

fun CoroutineScope.generateWork() = produce {
    for (i in 1..10_000) {
        send("page $i")
    }
    close()
}

private fun CoroutineScope.doWork(
    id: Int,
    channel: ReceiveChannel<String>
) = launch(Dispatchers.Default) {
    for (p in channel) {
        println("Worker $id processed $p")
    }
}