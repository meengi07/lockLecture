package com.example.stock.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Stock(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var productId: Long? = null,
    var quantity: Long,
    ) {
    fun decrease(quantity: Long) {
        if(this.quantity - quantity < 0) {
            throw IllegalArgumentException("Quantity cannot be negative")
        }
        this.quantity -= quantity
    }
}


