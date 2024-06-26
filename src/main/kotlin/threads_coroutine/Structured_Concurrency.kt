package org.example.threads_coroutine

import kotlinx.coroutines.*
import java.util.*

fun main() = runBlocking {
    val parent = launch(Dispatchers.Default) {
        supervisorScope {

            val children = List(10) { childId ->
                launch {
                    for (i in 1..1_000_000) {
                        UUID.randomUUID()

                        if (i % 100_000 == 0) {
                            println("$childId - $i")
                            yield()
                        }
                        if (childId == 3 && i == 100_000) {
                            throw RuntimeException("Something bad happened")
                        }
                    }
                }
            }
        }
    }
}