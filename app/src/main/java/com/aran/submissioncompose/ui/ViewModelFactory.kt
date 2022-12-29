package com.aran.submissioncompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aran.submissioncompose.data.FruitRepository
import com.aran.submissioncompose.ui.screen.detail.DetailViewModels
import com.aran.submissioncompose.ui.screen.cart.CartViewModel
import com.aran.submissioncompose.ui.screen.home.HomeViewModel


class ViewModelFactory(private val repository: FruitRepository) :
    ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repository) as T
            } else if (modelClass.isAssignableFrom(DetailViewModels::class.java)) {
                return DetailViewModels(repository) as T
            } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                return CartViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

}