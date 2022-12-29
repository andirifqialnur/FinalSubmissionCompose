package com.aran.submissioncompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aran.submissioncompose.ui.theme.Shapes
import com.aran.submissioncompose.R
import com.aran.submissioncompose.ui.theme.OrangePrimary
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun CartItem(
    fruitId: Long,
    image: Int,
    name: String,
    price: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(R.string.price, price),
                color = OrangePrimary,
                style = MaterialTheme.typography.subtitle2
            )
        }
        ProductCounter(
            orderId = fruitId,
            orderCount = count,
            onCartIncreased = { onProductCountChanged(fruitId, count + 1) },
            onCartDecreased = { onProductCountChanged(fruitId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    SubmissionComposeTheme {
        CartItem(
            1, R.drawable.apple, "Apple", 14200, 0,
            onProductCountChanged = { fruitId, count -> },
        )
    }
}