package com.app.cryptotracker.presentation.coins

import com.app.cryptotracker.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)