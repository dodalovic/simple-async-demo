package com.odalovic.simpleasyncdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleAsyncDemoApplication

fun main(args: Array<String>) {
    runApplication<SimpleAsyncDemoApplication>(*args)
}
