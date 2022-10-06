package com.example.crypto_currency_app.data.repository

import com.example.crypto_currency_app.data.CoinPaprikaApi
import com.example.crypto_currency_app.data.remote.dto.CoinDTO
import com.example.crypto_currency_app.data.remote.dto.CoinDetailDTO
import com.example.crypto_currency_app.domain.repository.CoinRepository
import javax.inject.Inject


/**
 * Implements CoinRepository, inject the api in the constructor,
 * use cases will use the repository to access the API, and
 * forward the information to the view models
 *
 * This looks like boilerplate code but it's useful in bigger projects
 */
class CoinRepositoryImpl @Inject constructor(
    private  val api: CoinPaprikaApi
) : CoinRepository {

     override suspend fun getCoins(): ArrayList<CoinDTO> {
        return api.getCoins()

    }

    override suspend fun getCoinById(coinId: String): CoinDetailDTO {
          return api.getCoinById(coinId)
    }
}