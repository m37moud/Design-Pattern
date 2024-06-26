package org.example.threads_coroutine

import kotlinx.coroutines.*

fun main() = runBlocking {
    val cancellable = launch {
        try {
            for (i in 1..1000) {
                println("Cancellable: $i")
                yield()
            }
        } catch (e: CancellationException) {
            e.printStackTrace()
        }
    }

    val notCancellable = launch {
        for (i in 1..10_000) {
            if (i % 100 == 0) {
                println("Not cancellable $i")
            }
        }
    }
    /**
     * coroutine check it will cancel or not if
     * another function found in coroutine
     */
    println("Canceling cancellable")
    cancellable.cancel()
    println("Canceling not cancellable")
    notCancellable.cancel()
    runBlocking {
        cancellable.join()
        notCancellable.join()
    }


}