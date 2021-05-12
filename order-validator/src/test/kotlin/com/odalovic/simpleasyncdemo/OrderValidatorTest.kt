package com.odalovic.simpleasyncdemo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

internal class OrderValidatorTest {

    @Test
    fun `Validate should return list of valid items`() {
        val order = Order(
            id = UUID.randomUUID(), items = listOf(
                Order.OrderItem("foo-1", quantity = 1),
                Order.OrderItem("foo-2", quantity = 2),
                Order.OrderItem("foo-3", quantity = 3),
                Order.OrderItem("foo-4", quantity = 4),
                Order.OrderItem("foo-5", quantity = 5),
                Order.OrderItem("foo-6", quantity = 6),
                Order.OrderItem("foo-7", quantity = 7),
                Order.OrderItem("foo-8", quantity = 8),
                Order.OrderItem("foo-9", quantity = 9),
                Order.OrderItem("foo-10", quantity = 10)
            )
        )
        val (id, items) = OrderValidator().validate(order)
        id shouldBe order.id
        items.size shouldBe 4
    }

    @Test
    fun `validate should return order with items as empty list`() {
        val order = Order(
            id = UUID.randomUUID(), items = listOf(
                Order.OrderItem("foo-5", quantity = 5),
                Order.OrderItem("foo-6", quantity = 6),
                Order.OrderItem("foo-7", quantity = 7),
                Order.OrderItem("foo-8", quantity = 8),
                Order.OrderItem("foo-9", quantity = 9),
                Order.OrderItem("foo-10", quantity = 10)
            )
        )
        val (id, items) = OrderValidator().validate(order)
        id shouldBe order.id
        items.size shouldBe 0
    }
}