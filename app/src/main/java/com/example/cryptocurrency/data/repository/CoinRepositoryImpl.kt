package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.data.remote.dto.ToCoin
import com.example.cryptocurrency.data.remote.dto.ToCoinDetail
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
            try {
                emit(Resource.Loading<List<Coin>>())
                val coins = api.getCoins().map { it.ToCoin() }
                emit(Resource.Success<List<Coin>>(coins))
            } catch (e: HttpException) {
                emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An error occured"))
            } catch (e: java.io.IOException) {
                emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Cfez error"))
            }
        }


    override suspend fun getCoinsById(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coinDetail = api.getCoinById(coinId).ToCoinDetail()
            emit(Resource.Success<CoinDetail>(coinDetail))
        }catch (e: HttpException)
        {
            emit(Resource.Error<CoinDetail>(e.message()))
        }catch (e: IOException)
        {
            emit((Resource.Error(e.localizedMessage?:"Error occured")))
        }
    }

}