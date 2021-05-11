package com.odalovic.simpleasyncdemo

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderConsumer(private val ordersRegistry: OrdersRegistry) {

    private val logger = KotlinLogging.logger {}

    @KafkaListener(topics = ["order_validated"])
    fun onOrderValidatedEvent(o: Order) {
        ordersRegistry.update(o)
        logger.info { "Order ${o.id} processed" }
    }
}