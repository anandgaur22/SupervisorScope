package com.example.supervisorscope

/*

fun main() = runBlocking {
    supervisorScope {
        launch {
            delay(500)
            println("Child Coroutine 1 Running")
        }
        launch {
            delay(1000)
            throw RuntimeException("Child Coroutine 2 Failed!") // this is  fail
        }
        launch {
            delay(1500)
            println("Child Coroutine 3 Running") // this is  continue
        }
    }
    println("SupervisorScope Completed")
}


* */