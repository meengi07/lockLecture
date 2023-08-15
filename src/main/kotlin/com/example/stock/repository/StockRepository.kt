package com.example.stock.repository

import com.example.stock.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface StockRepository : JpaRepository<Stock, Long> {
}