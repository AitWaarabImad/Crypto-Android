package com.example.cryptocurrency.domain.model

data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
