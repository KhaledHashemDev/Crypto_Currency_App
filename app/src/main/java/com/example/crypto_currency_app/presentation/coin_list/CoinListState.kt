package com.example.crypto_currency_app.presentation.coin_list

import com.example.crypto_currency_app.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: ArrayList<Coin> = arrayListOf(),
    val error: String = ""
    )


