package com.aran.submissioncompose.ui.screen.cart

import com.aran.submissioncompose.model.OrderFruit

data class CartState (
    val orderFruit: List<OrderFruit>,
    val totalPrice: Int
)