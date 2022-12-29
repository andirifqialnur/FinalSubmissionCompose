package com.aran.submissioncompose.ui.screen

import android.telecom.Call.Details
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aran.submissioncompose.di.Injection
import com.aran.submissioncompose.ui.ViewModelFactory
import com.aran.submissioncompose.ui.common.UiState
import com.aran.submissioncompose.ui.screen.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aran.submissioncompose.model.OrderFruit
import com.aran.submissioncompose.ui.components.FruitItem
import androidx.compose.foundation.lazy.grid.items

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getAllFruits()
            }
            is UiState.Success -> {
                HomeContent(
                    orderFruit = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderFruit: List<OrderFruit>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderFruit) { data ->
            FruitItem(
                image = data.fruit.image,
                name = data.fruit.name,
                price = data.fruit.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.fruit.id)
                }
            )
        }
    }
}