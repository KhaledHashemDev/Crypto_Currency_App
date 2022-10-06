package com.example.crypto_currency_app.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_currency_app.domain.use_case.get_coin.GetCoinUseCase
import com.example.crypto_currency_app.utils.Constants
import com.example.crypto_currency_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * We moved most of our business logic from view model to
 * use cases in clean architecture, but why do we have them now?
 * To maintain our state, they keep the UI state but with less business logic
 */

@HiltViewModel //injecting the use case
class CoinDetailViewModel @Inject constructor (
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle? // a bundle that contains info about the saved state, used to restore the app from process deaths, it also contains navigation parameter
        ) : ViewModel() {

    //vmstate Live Template, only the view model touches it
            private val _state = mutableStateOf(CoinDetailState())
            val state: State<CoinDetailState> = _state

    init {
        savedStateHandle?.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }
    //function that calls our GetCoinsUseCase and puts the data inside the state object
    //to display that in the UI
           private fun getCoin(coinId: String) {
        //overwrote the invoke function earlier for the use case which allows us to call the use case as a function
               getCoinUseCase(coinId).onEach { result ->
                   when(result) {
                       is Resource.SUCCESS -> {
                           _state.value = CoinDetailState(coin = result.data)
                       }
                       is Resource.ERROR -> {
                              _state.value = CoinDetailState(error = result.message ?: "An unexpected error occured")
                       }
                       is Resource.LOADING -> {
                           _state.value = CoinDetailState(isLoading = true)
                       }

                   }

               }.launchIn(viewModelScope) //because it's a flow which is asynchronous, we need to launch the coroutine (in viewModel scope)
           }

        }