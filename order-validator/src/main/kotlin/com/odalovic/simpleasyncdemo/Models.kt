package com.odalovic.simpleasyncdemo

import java.util.*

data class Order(val id: UUID, var items: List<OrderItem>) {
    data class OrderItem(val productName: String, val quantity: Int)
}