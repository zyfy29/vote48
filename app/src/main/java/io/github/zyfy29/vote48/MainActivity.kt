package io.github.zyfy29.vote48

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.zyfy29.vote48.ui.FanIdolScreen
import io.github.zyfy29.vote48.ui.IdolFanScreen
import io.github.zyfy29.vote48.ui.IdolScreen
import io.github.zyfy29.vote48.ui.MainViewModel
import io.github.zyfy29.vote48.ui.theme.Vote48Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Vote48Theme {
                MainNav()
            }
        }
    }
}

@Composable
fun MainNav() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    val idolUiState by viewModel.idolUiState.collectAsState()
    val idolFanUiState by viewModel.idolFanUiState.collectAsState()
    val fanIdolUiState by viewModel.fanIdolUiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "idol",
    ) {
        composable("idol") { IdolScreen(
            uiState = idolUiState,
            onRefresh =  viewModel::fetchIdolList,
            onViewDetail = { idolItem ->
                viewModel.fetchIdolFanList(idolItem)
                navController.navigate("idol-fan")
            }
        ) }
        composable("idol-fan") { IdolFanScreen(
            uiState = idolFanUiState,
            onViewDetail = { idolFanItem ->
                viewModel.fetchFanIdolList(idolFanItem)
                navController.navigate("fan-idol")
            },
            onRefresh = { viewModel.fetchIdolFanList(idolFanUiState.idolItem) },
        ) }
        composable("fan-idol") { FanIdolScreen(
            uiState = fanIdolUiState,
            onRefresh = { viewModel.fetchFanIdolList(fanIdolUiState.fan) }
        ) }
    }
}