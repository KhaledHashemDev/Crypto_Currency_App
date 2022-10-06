package com.example.crypto_currency_app.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crypto_currency_app.R
import com.example.cryptocurrencyapp.presentation.Screen
import com.example.crypto_currency_app.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController, //nav controller is needed because we want to navigate to details screen
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Surface {
        Box(modifier = Modifier.fillMaxSize()) {

            Column {
                androidx.compose.foundation.Image(painter = painterResource(id = R.drawable.ic_baseline_currency_bitcoin_24),
                    contentDescription = "BTC",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .size(50.dp, 50.dp)
                )

                SearchBar(
                    hint = "Search..",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){


                    viewModel.searchCoinsList(it)
                }


                LazyColumn(modifier = Modifier.fillMaxSize()) {



                    items(state.coins) { coin ->

                        Spacer(modifier = Modifier.height(5.dp))

                        CoinListItem(
                            coin = coin,
                            onItemClick = {
                                navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                            }
                        )
                        Divider()
                    }
                 }
            }
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }

    }





@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {


    var text by remember {
        mutableStateOf("")
    }
    var isHint by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHint = it.isFocused != true
                }

        )
        if(isHint){
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }
}


