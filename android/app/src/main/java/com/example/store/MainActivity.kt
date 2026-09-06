package com.example.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.store.api.HealthCheckApi
import com.example.store.ui.BackendStatus
import com.example.store.ui.MainViewModel
import com.example.store.ui.theme.StoreTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Инициализация Retrofit
        // 10.0.2.2 — специальный IP эмулятора Android для доступа к localhost вашего ПК
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.120:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(HealthCheckApi::class.java)
        val viewModel = MainViewModel(api)

        setContent {
            StoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BackendStatusScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackendStatusScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val status by viewModel.status.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            when (val currentStatus = status) {
                BackendStatus.Loading -> Text("Проверка соединения с Java-бекендом...")
                BackendStatus.Up -> Text("Бекенд работает! 🟢")
                is BackendStatus.Down -> Text("Бекенд не отвечает 🔴\n${currentStatus.message}")
            }
        }
    }
}