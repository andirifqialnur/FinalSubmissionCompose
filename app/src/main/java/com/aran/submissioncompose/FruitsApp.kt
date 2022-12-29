package com.aran.submissioncompose

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.view.RoundedCorner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aran.submissioncompose.model.Fruit
import com.aran.submissioncompose.ui.theme.OrangePrimary
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okio.use
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aran.submissioncompose.ui.navigation.NavigationItem
import com.aran.submissioncompose.ui.navigation.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aran.submissioncompose.ui.screen.AboutScreen
import com.aran.submissioncompose.ui.screen.HomeScreen
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.aran.submissioncompose.ui.screen.CartScreen
import com.aran.submissioncompose.ui.screen.detail.DetailScreen

// Fruits App Compose Function

@Composable
fun FruitApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailFruit.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { fruitId ->
                        navController.navigate(Screen.DetailFruit.createRoute(fruitId))
                    }
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = { message ->
                        shareOrder(context, message)
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailFruit.route,
                arguments = listOf(navArgument("fruitId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("fruitId") ?: -1L
                DetailScreen(
                    fruitId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

private fun shareOrder(
    context: Context, summary: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.fruit_shop))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.fruit_shop)
        )
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.Info,
                screen = Screen.About
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FruitDataGridItemPreview() {
    SubmissionComposeTheme{
        FruitApp()
    }
}


