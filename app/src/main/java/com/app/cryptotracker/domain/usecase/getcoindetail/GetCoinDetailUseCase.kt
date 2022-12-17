package com.app.cryptotracker.domain.usecase.getcoindetail

import com.app.cryptotracker.common.Resource
import com.app.cryptotracker.data.remote.dto.getCoinDetail
import com.app.cryptotracker.data.remote.dto.toCoin
import com.app.cryptotracker.domain.model.Coin
import com.app.cryptotracker.domain.model.CoinDetail
import com.app.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).getCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your network connection."))
        }
    }
}