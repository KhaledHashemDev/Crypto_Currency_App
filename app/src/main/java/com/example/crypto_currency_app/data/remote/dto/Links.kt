package com.example.crypto_currency_app.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
    val website: List<String>, //and this for external website link
    val youtube: List<String> //might use this for exo player
)

