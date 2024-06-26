package org.example.threads_coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
  runBlocking {
      launch {
          println(Thread.currentThread().name)
      }
      GlobalScope.launch {
          println(Thread.currentThread().name)
      }
  }
}