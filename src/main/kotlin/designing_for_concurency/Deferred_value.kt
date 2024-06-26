package org.example.designing_for_concurency

import kotlinx.coroutines.*
import kotlin.random.Random


fun main() {
    runBlocking {
        val value = valueAsync()
        println(value.await())
    }
}

suspend fun valueAsync(): Deferred<String> = coroutineScope {
    val deferred = CompletableDeferred<String>()
    launch {
        delay(1000)
        if (Random.nextBoolean()) {
            deferred.complete("OK")
        } else {
            deferred.completeExceptionally(
                RuntimeException()
            )
        }
    }
    deferred
}