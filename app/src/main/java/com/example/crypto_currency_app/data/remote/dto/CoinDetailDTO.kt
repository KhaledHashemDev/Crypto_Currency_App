package com.example.crypto_currency_app.data.remote.dto


import com.example.crypto_currency_app.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName


/**
 * DTO stands for Data Transfer Object
 */

data class CoinDetailDTO(
    val description: String, //needed
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val id: String, //
    @SerializedName("is_active")
    val isActive: Boolean, //
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String,
    val links: Links, //List of external links
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>,
    val logo: String,
    val message: String,
    val name: String, //
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("proof_type")
    val proofType: String,
    val rank: Int, //
    @SerializedName("started_at")
    val startedAt: String,
    val symbol: String, //
    val tags: List<Tag>?, //Tag is an object, tags is a list of Tag objects
    val team: List<TeamMember>, //List of team members, needed!
    val type: String,
    val whitepaper: Whitepaper
)


fun CoinDetailDTO.toCoinDetail() : CoinDetail {
return CoinDetail(
coinId = id,
name = name,
description = description,
symbol = symbol,
rank = rank,
isActive = isActive,
tags = tags?.map { it.name},
logo = logo,
team = team.filterNotNull(),
    //links = links.youtube.toMutableList().indexOf(0.toString()).toString()
)

}