package com.example.crypto_currency_app.data

import com.example.crypto_currency_app.data.remote.dto.CoinDTO
import com.example.crypto_currency_app.data.remote.dto.CoinDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Retrofit API interface
 * Defines the different functions and routes we want to accept from API
 * we will have two functions ,one to get all coins and one to get details about a specific
 * coin with  a specific ID
 */
interface CoinPaprikaApi {

//suspend functions are executed asynchronously in coroutines
    @GET("/v1/coins")
    suspend fun getCoins() : ArrayList<CoinDTO> //to return List<CoinDTO>,
   // CoinDTO (our JSON object) and Coin (our domain) had to be created first, along with the mapping.

    @GET("/v1/coins/{coinId}") //retrofit will replace any word between {}
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDTO //to return CoinDetailDTO,
    // CoinDetailDTO (our JSON object) and CoinDetail (our domain) had to be created first, along with the mapping.


}