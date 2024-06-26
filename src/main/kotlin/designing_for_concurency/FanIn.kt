package org.example.designing_for_concurency

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

fun main() {
    runBlocking {
        val workChannel = generateWork2()
        val resultChannel = Channel<String>()
        val workers = List(10) {
            doAsyncWork(workChannel, resultChannel)
        }
        resultChannel.consumeEach {
            println(it)
        }
    }

}

fun CoroutineScope.generateWork2() = produce {
    for (p in 1..10_000) {
        send("page $p")
    }
    close()
}

fun CoroutineScope.doAsyncWork(
    channel: ReceiveChannel<String>,
    resultChannel: Channel<String>
) = async(Dispatchers.Default) {
    for (c in channel) {
        resultChannel.send(c.repeat(2))
    }

}