package com.example.crypto_currency_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Gives dagger.hilt info about the application so dagger.hilt has access
 * to the application context if we need that for application dependencies,
 * we need to add this to the manifest
 */

@HiltAndroidApp
class CoinApplication : Application()
