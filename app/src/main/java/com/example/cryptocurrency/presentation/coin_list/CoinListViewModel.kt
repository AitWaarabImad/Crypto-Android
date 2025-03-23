package com.example.cryptocurrency.presentation.coin_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUsecase
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {
        //private val _state : MutableStateFlow<CoinListeState> = MutableStateFlow(CoinListeState())
    //val state = _state.asStateFlow()
        private val _state = mutableStateOf(CoinListeState())
    val state: State<CoinListeState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        Log.d("CoinListViewModel", "Fetching coins...")
        viewModelScope.launch {
            getCoinsUseCase.invoke().onEach { result ->
                Log.d("CoinListViewModel", "Api result coins: $result")

                when (result) {
                    is Resource.Success -> {
                        Log.d("CoinListViewModel", "Coins received: ${result.data?.size ?: 0}")
                        _state.value = CoinListeState(Coins = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        Log.e("CoinListViewModel", "Error: ${result.message}")
                        _state.value = CoinListeState(error = result.message ?: "error occured")
                    }

                    is Resource.Loading -> {
                        Log.d("CoinListViewModel", "Loading...")
                        _state.value = CoinListeState(isLoading = true)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}