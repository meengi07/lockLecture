package com.example.stock.service

import com.example.stock.repository.StockRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockRepository: StockRepository,
) {

    @Transactional
    @Synchronized
    fun decrease(id: Long, quantity: Long) {
        // stock 조회
        // 재고를 감소시킨 뒤
        // 갱신된 값을 저장
        val stock = stockRepository.findById(id).orElseThrow()
        stock.decrease(quantity)
        stockRepository.saveAndFlush(stock)

    }

}