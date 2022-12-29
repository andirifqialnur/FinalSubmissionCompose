package com.aran.submissioncompose.ui.navigation

import com.aran.submissioncompose.model.Fruit

sealed class Screen(val route: String) {
    object Home: Screen("Home")
    object Cart: Screen("Cart")
    object About: Screen("About")
    object DetailFruit: Screen("home/{fruitId}") {
        fun createRoute(fruitId: Long) = "home/$fruitId"
    }
}