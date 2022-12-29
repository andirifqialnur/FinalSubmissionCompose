package com.aran.submissioncompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.aran.submissioncompose.ui.theme.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aran.submissioncompose.R
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme
@Composable
fun FruitItem(
    image: Int,
    name: String,
    price: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        Text(
            text = stringResource(R.string.price, price),
            fontSize = 12.sp,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Medium,
                color = com.aran.submissioncompose.ui.theme.OrangePrimary
            ),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FruitItemPreview() {
    SubmissionComposeTheme {
        FruitItem(R.drawable.apple, "Apple", 5700)
    }
}