package com.odalovic.simpleasyncdemo

import com.odalovic.simpleasyncdemo.Order.OrderItem
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*
import kotlin.random.Random

@Component
class OrderProducer(
    private val template: KafkaTemplate<String, Order>,
    private val ordersRegistry: OrdersRegistry
) {

    private val logger = KotlinLogging.logger {}

    @Scheduled(fixedDelay = 3000L)
    fun producePeriodically() {
        val orderId = UUID.randomUUID()
        val orderItems = List(10) { OrderItem(productName = "${orderId}-p-$it", quantity = Random.nextInt(10)) }
        val order = Order(id = orderId, items = orderItems)
        ordersRegistry.add(order)
        template.send("order_created", order)
            .also { logger.info { "Produced order with ID ${order.id}" } }
    }
}