package org.example.threads_coroutine.coroutine

import kotlinx.coroutines.*
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    /**
     * compare time taken between two funcation
     */
//    threadFun()
//    coroutineFunc()
    runBlocking {
        val job: Deferred<UUID> = getUUIDByAsyncCoroutine()
        println(job.await())
    }


}

fun threadFun() {
    val latch = CountDownLatch(10_000)
    val counter = AtomicInteger()
    val start = System.currentTimeMillis()

    for (i in 1..10_000) {
        thread {
            counter.incrementAndGet()
            Thread.sleep(100)
            counter.incrementAndGet()
            latch.countDown()
        }

    }
    latch.await(10, TimeUnit.SECONDS)
    println("it took ${System.currentTimeMillis() - start} ms to make ${counter.get() / 2} thread")


}

/**
 * this function to compare with threadFun() in the top
 */
@OptIn(DelicateCoroutinesApi::class)
fun coroutineFunc() {
    val latch = CountDownLatch(10_000)
    val counter = AtomicInteger()
    val start = System.currentTimeMillis()
    for (i in 1..10_000) {
        GlobalScope.launch {
            counter.incrementAndGet()
            delay(100)
            counter.incrementAndGet()
            latch.countDown()
        }
    }
    latch.await(10, TimeUnit.SECONDS)
    println("its took ${System.currentTimeMillis() - start} ms to make ${counter.get() / 2} coroutine")
}

@OptIn(DelicateCoroutinesApi::class)
fun getUUIDByAsyncCoroutine() = GlobalScope.async {
    UUID.randomUUID()
}