package com.odalovic.simpleasyncdemo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

internal class OrderValidatorTest {

    @Test
    fun `Validate should return list of valid items`() {

        val order = Order(
            id = UUID.randomUUID(), items = (1..10).map {
                Order.OrderItem("foo-$it", quantity = it)
            })
        val (id, items) = OrderValidator().validate(order)
        id shouldBe order.id
        items.size shouldBe 4
    }

    @Test
    fun `validate should return order with items as empty list`() {
        val order = Order(
            id = UUID.randomUUID(), items = (5..10).map {
                Order.OrderItem("foo-$it", quantity = it)
            })
        val (id, items) = OrderValidator().validate(order)
        id shouldBe order.id
        items.size shouldBe 0
    }
}