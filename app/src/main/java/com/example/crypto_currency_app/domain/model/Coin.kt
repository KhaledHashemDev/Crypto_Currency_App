package com.example.crypto_currency_app.domain.model


//Domain - only contains the data needed
data class Coin(
    var id: String,
    var isActive: Boolean,
    var name: String,
    var rank: Int,
    var symbol: String,
    //val logo: String
    )

