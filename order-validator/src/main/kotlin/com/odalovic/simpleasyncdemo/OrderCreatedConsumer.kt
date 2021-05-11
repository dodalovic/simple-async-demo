package com.odalovic.simpleasyncdemo

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

private const val INCOMING_TOPIC = "order_created"
private const val OUTGOING_TOPIC = "order_validated"

@Component
class OrderCreatedConsumer(
    private val template: KafkaTemplate<String, Order>,
    private val orderValidator: OrderValidator
) {
    private val logger = KotlinLogging.logger {}

    @KafkaListener(topics = [INCOMING_TOPIC])
    fun onOrderCreatedEvent(o: Order) {
        logger.info { "Handling order ${o.id}" }
        val validatedOrder = orderValidator.validate(o)
        template.send(OUTGOING_TOPIC, validatedOrder)
    }
}