package org.example.designing_for_concurency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlin.random.Random


/**
 * this design pattern aim to collect first data which come from any producer
 * if we have more than one producer produce data
 * it selects the first result and calls it winner
 * and let others and call it losser.
 * (if the two results come at the same time, it picks the first)
 */
fun main() {
    runBlocking {
        while (true) {
            val winner = select<Pair<String, String>> {
                preciseWeather().onReceive { preciseWeatherResult ->
                    preciseWeatherResult
                }
                weatherToday().onReceive { weatherTodayResult ->
                    weatherTodayResult
                }
            }
            println(winner)
            delay(1000)
        }
    }
}

fun CoroutineScope.preciseWeather() = produce {
    delay(Random.nextLong(100))
    send("Precise Weather" to "+25c")
}

fun CoroutineScope.weatherToday() = produce {
    delay(Random.nextLong(100))
    send("Weather Today" to "+24c")
}