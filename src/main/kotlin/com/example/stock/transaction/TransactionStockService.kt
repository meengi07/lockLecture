package com.example.stock.transaction

import com.example.stock.service.StockService

class TransactionStockService(
    private val stockService: StockService
) {
    fun decrease(id: Long, quantity: Long) {
        startTransaction()

        stockService.decrease(id, quantity)

        endTransaction()
    }


    private fun startTransaction() {
        println("트랜잭션 시작")
    }

    private fun endTransaction() {
        println("트랜잭션 종료")
    }
}