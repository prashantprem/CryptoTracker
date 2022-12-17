package com.app.cryptotracker.domain.repository

import com.app.cryptotracker.data.remote.dto.CoinDetailDto
import com.app.cryptotracker.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}