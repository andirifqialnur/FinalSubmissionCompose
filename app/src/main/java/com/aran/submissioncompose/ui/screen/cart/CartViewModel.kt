package com.aran.submissioncompose.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aran.submissioncompose.data.FruitRepository
import com.aran.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: FruitRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddOrderFruits() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderFruits()
                .collect { orderFruit ->
                    val totalPrice =
                        orderFruit.sumOf { it.fruit.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderFruit, totalPrice))
                }
        }
    }

    fun updateOrderFruits(fruitId: Long, count: Int) {
        viewModelScope.launch {
            repository.updatedOrderFruit(fruitId, count)
                .collect {isUpdated ->
                    if (isUpdated) {
                        getAddOrderFruits()
                    }
                }
        }
    }
}