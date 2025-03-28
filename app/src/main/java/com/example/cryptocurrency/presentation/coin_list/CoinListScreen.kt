package com.example.cryptocurrency.presentation.coin_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coin_list.components.CoinListItem

@Composable

fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
)
{
    val state = viewModel.state.value
    Log.d("CoinListScreen", "State: $state")

    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.Coins) { it ->
                CoinListItem(it) { navController.navigate(Screen.CoinDetailScreen.route + "/${it.id}") }
            }
        }
        if (state.error.isNotBlank())
        {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading)
        {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}