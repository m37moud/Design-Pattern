package org.example.designing_for_concurency

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * mutex protect threads to use shared state
 * mean : in our example it doesn't let another coroutine to update counter until the previous one is finshed
 * it locks the coroutine mechanism
 * coroutine try to update the counter
 * if it found another coroutine update the counter it wait until he finish
 * after he finish it will lock until update the counter then it release the lock
 * mutex in kotlin is diffrent from java
 * in java the lock we got from mutex block the thread untill we got the lock again
 * in kotlin it suspend the coroutine
 */
fun main() {
    runBlocking {
        var counter = 0
        val mutex = Mutex()
        val jobs = List(10) {
            async(Dispatchers.Default) {
                repeat(1000) {
                    mutex.withLock {
                        counter++
                    }
                }
            }
        }
        jobs.awaitAll()

        println(counter)
    }
    /**
     * if we have a critical task may be got a exception we do this
     * note if we dosnt use finally block the mutex will remain locked the coroutine
     * and no other coroutine can use it
     * for that we use withLock function
     */
    runBlocking {
        var count = 0
        val mutex = Mutex()
        val jobs = List(10) {
            async(Dispatchers.Default) {
                try {
                    mutex.lock()
                    count++
                } finally {
                    mutex.unlock()

                }
            }
        }
        jobs.awaitAll()
        println(count)
    }
}