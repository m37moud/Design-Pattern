package org.example.data_flow.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    producer()
}



/**
 * in producer, define the type of value automatically
 * and handle close
 */
@OptIn(ExperimentalCoroutinesApi::class)
private fun producer(){
    runBlocking {
        val chan = produce {
            (1..10).forEach {
                send(it)
            }
        }
        launch {
            /**
             * it called consumer which is receiving the data
             */
//            for (c in chan){
//                println(c)
//            }
            /**
             * use consumeEach instead for loop
             */
            chan.consumeEach {
                println(it)
            }
        }
    }
}