package com.example.crypto_currency_app.domain.repository

import com.example.crypto_currency_app.data.remote.dto.CoinDTO
import com.example.crypto_currency_app.data.remote.dto.CoinDetailDTO




/**
 * here we only define the functions we had in our API
* helpful for test cases, since we done want to use the actual because
 * it will take more time.
 * implementation of the repository to test the use cases
 */

interface CoinRepository {

    //repository definitions
    suspend fun getCoins() : ArrayList<CoinDTO>

    suspend fun getCoinById(coinId: String) : CoinDetailDTO
}