package com.example.stock.service

import com.example.stock.repository.StockRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PessimisticLockStockService(
    private val stockRepository: StockRepository
) {
    @Transactional
    fun decrease(id: Long, quantity: Long) {
        // stock 조회
        // 재고를 감소시킨 뒤
        // 갱신된 값을 저장
        val stock = stockRepository.findByIdWithPessimisticLock(id)
        stock.decrease(quantity)
        stockRepository.save(stock)
    }
}