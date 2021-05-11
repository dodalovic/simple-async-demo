package com.odalovic.simpleasyncdemo

import org.springframework.stereotype.Component

@Component
class OrderValidator {
    fun validate(o: Order): Order {
        val validItems = o.items.filter { it.productName.last().toInt() < 5 }
        return o.copy(items = validItems)
    }
}