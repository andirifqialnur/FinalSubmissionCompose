package com.aran.submissioncompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aran.submissioncompose.data.FruitRepository
import com.aran.submissioncompose.model.Fruit
import com.aran.submissioncompose.model.OrderFruit
import com.aran.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModels(
    private val repository: FruitRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderFruit>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderFruit>>
        get() = _uiState

    fun getFruitById(fruitId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderFruitById(fruitId))
        }
    }

    fun addToCart(fruit: Fruit, count: Int) {
        viewModelScope.launch {
            repository.updatedOrderFruit(fruit.id, count)
        }
    }

}