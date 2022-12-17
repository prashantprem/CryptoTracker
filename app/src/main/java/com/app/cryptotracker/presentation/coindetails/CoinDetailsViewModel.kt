package com.app.cryptotracker.presentation.coindetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cryptotracker.common.Constants
import com.app.cryptotracker.common.Resource
import com.app.cryptotracker.domain.model.Coin
import com.app.cryptotracker.domain.usecase.getcoindetail.GetCoinDetailUseCase
import com.app.cryptotracker.domain.usecase.getcoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
       savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
           getCoin(coinId)
       }
    }

    private fun getCoin(coinId: String) {
        getCoinsUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coins = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}