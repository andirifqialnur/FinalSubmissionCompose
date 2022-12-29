package com.aran.submissioncompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aran.submissioncompose.data.FruitRepository
import com.aran.submissioncompose.model.OrderFruit
import com.aran.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FruitRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderFruit>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderFruit>>>
        get() = _uiState

    fun getAllFruits() {
        viewModelScope.launch {
            repository.getAllFruits()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderFruits ->
                    _uiState.value = UiState.Success(orderFruits)
                }
        }
    }

}