package com.example.crypto_currency_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crypto_currency_app.ui.theme.Crypto_Currency_Theme
import com.example.cryptocurrencyapp.presentation.Screen
import com.example.crypto_currency_app.presentation.coin_detail.components.CoinDetailScreen
import com.example.crypto_currency_app.presentation.coin_list.CoinListScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Clean architecture extends(not literally) MVVM, it includes an extra "use cases" layer
 * which contains our business business logic, in mvvm we put all of our business logic
 * into the view model, we are trying to avoid making the view model layer do everything
 * we try to separate parts of a feature for our app.
 * A feature is a set of screens that are somehow related (single actions we can do within a feature)
 * we allow our code to be reusable, instead of having the logic in the view model,
 * to avoid having duplicate codes, it also help you understand what the project is
 * about by looking at the layers of our code (screaming architecture). *We now
 * separate our project into 3 layers. Presentation (UI) - contains composable, xml if used,
 * views, view models. Then Domain layer - contains our model - the actual entities that contain data,
 * repository definition (interfaces) and business logic (use cases) . Then the Data layer, contains our data
 * like api interface,  a data bases (we don't have one here), it contains repository implementations.
 *
 * We are using the same package structure as a scalable app, which also allows
 * for better separation of concerns, and helps us tp test those layers in isolation)
 *
 * Package components contains composable
 *
 * we will two use_cases(s) , one to get coins and one to get a single coin (details)
 *
 * dto - data transfer object, the object that our api returns (domain is distilled version of data)
 *
 * di - dependency injection - dagger hilt
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Crypto_Currency_App)

        setContent {
            Crypto_Currency_Theme{
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.CoinListScreen.route,
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

/**
 *
 * First we start with the API
 */