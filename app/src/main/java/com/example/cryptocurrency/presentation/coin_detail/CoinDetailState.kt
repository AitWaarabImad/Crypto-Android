package com.example.cryptocurrency.presentation.coin_detail

import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val coin : CoinDetail? = null,
    val isLoading: Boolean = true,
    val error: String = ""

)
