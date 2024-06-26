package org.example.designing_for_concurency

import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool


fun main() {
    runBlocking {
//        launch(Dispatchers.IO) {
//            for (i in 1..1000) {
//                println(Thread.currentThread().name)
//                yield()
//            }
//        }
        val forkJoinPool = ForkJoinPool(4).asCoroutineDispatcher()
        val myDispatcher = Executors
            .newFixedThreadPool(4)
            .asCoroutineDispatcher()
        repeat(1000) {
            launch(myDispatcher) {
                println(Thread.currentThread().name)
            }
        }
    }
}