package com.aran.submissioncompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aran.submissioncompose.di.Injection
import com.aran.submissioncompose.ui.ViewModelFactory
import com.aran.submissioncompose.ui.common.UiState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aran.submissioncompose.R
import com.aran.submissioncompose.ui.components.OrderButton
import com.aran.submissioncompose.ui.components.ProductCounter
import com.aran.submissioncompose.ui.theme.OrangePrimary
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme


@Composable
fun DetailScreen(
    fruitId: Long,
    viewModel: DetailViewModels = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getFruitById(fruitId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.fruit.image,
                    data.fruit.name,
                    data.fruit.price,
                    data.fruit.desc,
                    data.fruit.rating,
                    data.fruit.mineral,
                    data.fruit.vitC,
                    data.fruit.vitA,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.fruit, count)
                        navigateToCart()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    price: Int,
    desc: String,
    rating: String,
    mineral: String,
    vitC: String,
    vitA: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPrice by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp).clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = stringResource(R.string.price, price),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = OrangePrimary
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
                Row(
                    modifier = modifier.padding(top = 20.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Yellow
                    )
                    Text(
                        text = rating,
                        fontSize = 18.sp
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = mineral,
                        fontSize = 25.sp,
                    )
                    Text(
                        text = "Mineral",
                        fontSize = 15.sp
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = vitA,
                        fontSize = 25.sp,
                    )
                    Text(
                        text = "Vit A",
                        fontSize = 15.sp
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = vitC,
                        fontSize = 25.sp,
                    )
                    Text(
                        text = "Vit C",
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(4.dp).background(LightGray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductCounter(
                1,
                orderCount,
                onCartIncreased = { orderCount++ },
                onCartDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
            totalPrice = price * orderCount
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPrice),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailContentPreview() {
    SubmissionComposeTheme {
        DetailContent(
            R.drawable.apple,
            "Apple",
            14200,
            "ladi fala fnaj akfjna l lasflfa ldjnalgyhaefakug alnlfaf uha  lfjasdkfnal ahf asudn asdnasc asufpausfsnkasdnf au lsdnfawhl aldfnm",
            "4.5",
            "12.1",
            "16.3",
            "11",
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}
