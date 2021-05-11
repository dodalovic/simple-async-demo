package com.odalovic.simpleasyncdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SimpleAsyncDemoApplication

fun main(args: Array<String>) {
    runApplication<SimpleAsyncDemoApplication>(*args)
}
