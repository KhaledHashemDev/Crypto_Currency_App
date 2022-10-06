package com.example.crypto_currency_app.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_currency_app.domain.model.Coin
import com.example.crypto_currency_app.domain.use_case.get_coins.GetCoinsUseCase
import com.example.crypto_currency_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * We moved most of our business logic from view model to
 * use cases in clean architecture, but why do we have them now?
 * To maintain our state, they keep the UI state but with less business logic
 */

@HiltViewModel //injecting the use case
class CoinListViewModel @Inject constructor (
    private val getCoinsUseCase: GetCoinsUseCase,
        ) : ViewModel() {

    //vmstate Live Template, only the view model touches it
            private val _state =
                mutableStateOf(CoinListState())
          val state: State<CoinListState> = _state

    //for search purposes test
    private var coinsList = mutableStateOf<List<Coin>>(listOf())
    private var cachedCoinsList = listOf<Coin>()
    private var isSearchStarting = true
    private var isSearching = mutableStateOf(false)

    init {
        getCoins()
    }


    fun searchCoinsList(query: String){
        val listToSearch = if(isSearchStarting){
           coinsList.value
        } else {
            cachedCoinsList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                coinsList.value = cachedCoinsList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                //val iterate: Int = coins.size
                it.name.contains(query.trim(), ignoreCase = true) ||
                       it.rank.toString() == query.trim() ||
                     it.symbol.contains(query.trim(), ignoreCase = true)

            }

            if(isSearchStarting){
                cachedCoinsList = coinsList.value
                isSearchStarting = false
            }
            coinsList.value = results
            isSearching.value = true
        }
    }

    //function that calls our GetCoinsUseCase and puts the data inside the state object
    //to display that in the UI
      private fun getCoins() {


            //overwrote the invoke function earlier for the use case which allows us to call the use case as a function
            getCoinsUseCase().onEach { result ->
                when (result) {
                    is Resource.SUCCESS -> {
                        _state.value =
                            CoinListState(coins = result.data ?: arrayListOf())
                    }
                    is Resource.ERROR -> {
                        _state.value =

                                CoinListState(
                                    error = result.message ?: "An unexpected error occurred"
                                )

                    }
                    is Resource.LOADING -> {
                        _state.value = CoinListState(isLoading = true)
                    }

                }

            }.launchIn(viewModelScope)

    }

}





