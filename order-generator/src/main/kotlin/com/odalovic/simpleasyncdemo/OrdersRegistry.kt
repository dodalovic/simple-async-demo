package com.odalovic.simpleasyncdemo

import org.springframework.stereotype.Component

@Component
class OrdersRegistry {
    private val orders = mutableListOf<Order>()

    fun add(o: Order) {
        orders += o
    }

    fun update(o: Order) {
        orders.removeIf { it.id == o.id }
        add(o)
    }
}