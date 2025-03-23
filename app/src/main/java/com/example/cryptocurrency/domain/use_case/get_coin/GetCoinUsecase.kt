package com.example.cryptocurrency.domain.use_case.get_coin

import android.net.http.HttpException
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetCoinUsecase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> =
        coinRepository.getCoinsById(coinId)


}