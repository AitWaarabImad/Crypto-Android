package com.example.cryptocurrency

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coin_detail.CoinDetailScreen
import com.example.cryptocurrency.presentation.coin_list.CoinListScreen
import com.example.cryptocurrency.ui.theme.CryptoCurrencyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route, modifier = Modifier.padding(innerPadding)
                    )
                    {
                        composable(route = Screen.CoinListScreen.route)
                        {
                            CoinListScreen(
                                navController = navController,
                            )
                        }
                        composable (route = Screen.CoinDetailScreen.route + "/{coinId}")
                        {
                            CoinDetailScreen()
                        }
                    }
                }
//                Surface (modifier = Modifier.padding(vertical = 30.dp, horizontal = 5.dp),color = MaterialTheme.colorScheme.background)
//                {
//
//                }
            }
        }
    }

}

