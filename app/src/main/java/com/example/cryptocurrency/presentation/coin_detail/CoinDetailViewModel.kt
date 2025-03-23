package com.example.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUsecase: GetCoinUsecase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state = _state

    init {
        savedStateHandle.get<String>("coinId")?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String)
    {
        viewModelScope.launch {
            getCoinUsecase.invoke(coinId).onEach { result ->
                when (result) {

                    is Resource.Success -> {
                        _state.value = CoinDetailState(coin = result.data, isLoading = false)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailState(error = result.message ?: "An error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}