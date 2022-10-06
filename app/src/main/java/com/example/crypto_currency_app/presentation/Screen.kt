package com.example.cryptocurrencyapp.presentation

//helps us deal with routes for navigation
sealed class Screen(val route: String){
          object CoinListScreen: Screen("coin_list")
          object CoinDetailScreen: Screen("coin_detail_screen")
}
