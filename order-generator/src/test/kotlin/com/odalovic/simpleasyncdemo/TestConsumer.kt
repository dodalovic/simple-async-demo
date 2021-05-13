package com.odalovic.simpleasyncdemo

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class TestConsumer {

    private val logger = KotlinLogging.logger {}

    val latch = CountDownLatch(1)

    @KafkaListener(topics = ["topic"], groupId = "test")
    fun onEvent(o: Order) {
        logger.info { "Processed: $o" }
        latch.countDown()
    }
}