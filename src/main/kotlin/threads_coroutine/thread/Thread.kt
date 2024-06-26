package org.example.threads_coroutine.thread

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
//    sleepingPoolThread()
    sleepingThread2()

}

/**
 * daemon thread don't make jvm exiting ,
 * and good for non-critical background task
 */
fun normalThread() {
    repeat(2) { t ->
        thread(isDaemon = true) {
            for (i in 1..100) {
                println("thread $t = $i")
            }
        }

    }
}

fun synchronizedThread() {
    var counter = 0
    var latch = CountDownLatch(100_000)
    repeat(100) {
        thread {
            repeat(1000) {
                synchronized(latch) {
                    counter++
                    latch.countDown()
                }

            }
        }
    }
    latch.await()
    println("Counter $counter")
}

/**
 * this thread take 1 megabyte (1 m) size of ram for each thread
 * and it lead for exception or slow of performance
 */
fun sleepingThread() {
    val counter = AtomicInteger()
    try {
        for (i in 0..10_1000) {
            thread {
                println(counter.incrementAndGet())
                Thread.sleep(100)
            }
        }
    } catch (oome: OutOfMemoryError) {
        println("Spawned ${counter.get()} threads before       crashing")
        System.exit(-42)
    }
}

fun sleepingThread2() {
    val latch = CountDownLatch(10_000)
    val counter = AtomicInteger()
    val start = System.currentTimeMillis()
    try {
        for (i in 1..10_000) {
            thread {
                counter.incrementAndGet()
                Thread.sleep(100)
                counter.incrementAndGet()
                latch.countDown()
            }
        }
        latch.await(10, TimeUnit.SECONDS)
        println("Took me ${System.currentTimeMillis() - start}   millis to complete ${counter.get() / 2} tasks")


    } catch (oome: OutOfMemoryError) {
        println("Spawned ${counter.get()} threads before       crashing")
        System.exit(-42)
    }
}

/**
 *   setting the pool size to 1 of cores on your machine to 100 and 2000
 */
fun sleepingPoolThread() {
    val pool = Executors.newFixedThreadPool(100)
    val counter = AtomicInteger(0)
    val start = System.currentTimeMillis()

    try {
        for (i in 0..10_1000) {
            pool.submit {
                // Do something
                println(counter.incrementAndGet())
                // Simulate wait on IO
                Thread.sleep(100)
                // Do something again
                counter.incrementAndGet()
            }
        }
        //make sure that the pool terminates and give it 20 seconds to do so
        pool.awaitTermination(20, TimeUnit.SECONDS)
        pool.shutdown()
        println("Took me ${System.currentTimeMillis() - start}   millis to complete ${counter.get() / 2} tasks")
    } catch (oome: OutOfMemoryError) {
        println("Spawned ${counter.get()} threads before       crashing")
        System.exit(-42)
    }
}


