package com.aran.submissioncompose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aran.submissioncompose.R
import com.aran.submissioncompose.ui.theme.OrangePrimary
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onCartIncreased: (Long) -> Unit,
    onCartDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.size(width = 110.dp, height = 40.dp).padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, OrangePrimary),
            color = Color.Transparent,
            contentColor = OrangePrimary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = stringResource(R.string.minus),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onCartDecreased(orderId)
                    }
            )
        }
        Text(
            text = orderCount.toString(),
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, OrangePrimary),
            color = Color.Transparent,
            contentColor = OrangePrimary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = stringResource(R.string.plus),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onCartIncreased(orderId)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCounterPreview() {
    SubmissionComposeTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 0,
            onCartIncreased = { },
            onCartDecreased = { }
        )
    }
}