package com.example.cryptocurrency.domain.use_case.get_coins

import androidx.compose.ui.res.fontResource
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Coin>>> {
        return coinRepository.getCoins()
    }
}