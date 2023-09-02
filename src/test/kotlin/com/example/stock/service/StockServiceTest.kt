package com.example.stock.service

import com.example.stock.domain.Stock
import com.example.stock.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

@SpringBootTest
class StockServiceTest(
    @Autowired
    private var stockService: PessimisticLockStockService,
    @Autowired
    private var stockRepository: StockRepository
) {

    @BeforeEach
    fun setUp() {
        val stock = Stock(1L, null, 100L)
        stockRepository.saveAndFlush(stock)
    }

    @AfterEach
    fun setAfter() {
        stockRepository.deleteAll()
    }

    @Test
    fun 재고감소() {
        stockService.decrease(1L, 1L)
        val stock = stockRepository.findById(1L).get()
        println(stock)
        assertEquals(99, stock.quantity)
    }

    @Test
    @DisplayName("race condition problem")
    fun 동시에_100개의_요청() {
        val extutor: ExecutorService? = Executors.newFixedThreadPool(32)
        val latch: CountDownLatch = CountDownLatch(100)
        for(i in 1..100) {
            extutor?.submit {
                stockService.decrease(1L, 1L)
                latch.countDown()
            }
        }
        latch.await()
        val stock = stockRepository.findById(1L).get()
        assertEquals(0, stock.quantity)
    }

}