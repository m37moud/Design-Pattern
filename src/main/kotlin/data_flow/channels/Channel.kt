package org.example.data_flow.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    channel()

}

/**
 * in channel, we should define it manually
 * mean we should define the type of value
 * and handle close
 */
private fun channel() {
    runBlocking {
        val channel = Channel<Int>()
        /**
         * 1.make a coroutine to listen to channel
         * it called consumer
         */
        launch { //coroutine Builder

            for (c in channel) {
                println(c)

            }
        }
        /**
         * 2.send data of type integer to channel
         * 3.close the channel
         */
        (1..10).forEach {
            channel.send(it)
        }
        channel.close()
        /**
         * once channel is closed the coroutine break the loop which is listen for the channel
         * and coroutine if not found anything else to it will terminate
         */

    }
}

