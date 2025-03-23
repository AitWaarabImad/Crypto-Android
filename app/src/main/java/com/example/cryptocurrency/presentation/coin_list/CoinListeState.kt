package com.example.cryptocurrency.presentation.coin_list

import com.example.cryptocurrency.domain.model.Coin

data class CoinListeState(
    val isLoading: Boolean = false,
    val Coins: List<Coin> = emptyList(),
    val error : String = ""
)
