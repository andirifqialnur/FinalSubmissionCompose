package com.aran.submissioncompose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aran.submissioncompose.R
import com.aran.submissioncompose.ui.components.OrderButton
import com.aran.submissioncompose.ui.screen.cart.CartState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.aran.submissioncompose.ui.ViewModelFactory
import com.aran.submissioncompose.di.Injection
import com.aran.submissioncompose.ui.components.CartItem
import com.aran.submissioncompose.ui.screen.cart.CartViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aran.submissioncompose.ui.common.UiState


@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddOrderFruits()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onProductCountChanged = { rewardId, count ->
                        viewModel.updateOrderFruits(rewardId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderFruit.count(),
        state.totalPrice
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_cart),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        OrderButton(
            text = stringResource(R.string.total_order, state.totalPrice),
            enabled = state.orderFruit.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.orderFruit, key = { it.fruit.id }) { item ->
                CartItem(
                    fruitId = item.fruit.id,
                    image = item.fruit.image,
                    name = item.fruit.name,
                    price = item.fruit.price * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChanged,
                )
                Divider()
            }
        }
    }
}