package com.example.crypto_currency_app.presentation.coin_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crypto_currency_app.presentation.coin_detail.CoinDetailViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    //state.coin? a nullable coin detail element, we want to make sure its not null
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin -> //get info of that coin here
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp) //giving our column some padding
            ) {
                item {
                    //building the top section of text and active, using a single item block Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f) //so that we don't overlap the active text
                        )
                            Spacer(modifier = Modifier.height(15.dp))
                            Image(
                                painter = rememberGlidePainter(coin.logo),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(50.dp,50.dp)

                            )

                        Text(  //"active" text
                            text = if (coin.isActive) "active" else if (!coin.isActive) "inactive" else "Empty",
                            color = if (coin.isActive) Color.Green else if (!coin.isActive) Color.Red else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp)) //space to add the description part
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body2,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    //For the tags to be wrapped and displayed
                    FlowRow( //flow row wraps the elements if they exceed the bounds of the row, into the next line
                        mainAxisSpacing = 10.dp, //space horizontally between items
                        crossAxisSpacing = 10.dp, //space vertically between items
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags?.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    //for team members part
                    Text(
                        text = "Team Members",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coin.team) { teamMember ->
                    TeamListItem(teamMember = teamMember, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    )
                    Divider() //lines between each team member
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

/*
Image(
painter = rememberAsyncImagePainter("${coin.logo}"),
contentDescription = null,
modifier = Modifier.size(128.dp)

) */