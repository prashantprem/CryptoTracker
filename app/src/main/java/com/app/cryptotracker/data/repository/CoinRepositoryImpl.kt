package com.app.cryptotracker.data.repository

import com.app.cryptotracker.data.remote.Api
import com.app.cryptotracker.data.remote.dto.CoinDetailDto
import com.app.cryptotracker.data.remote.dto.CoinDto
import com.app.cryptotracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: Api
) : CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
       return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}