package com.app.cryptotracker.presentation.coindetails

import com.app.cryptotracker.domain.model.Coin
import com.app.cryptotracker.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = ""
)