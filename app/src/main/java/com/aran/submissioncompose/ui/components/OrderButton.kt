package com.aran.submissioncompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aran.submissioncompose.ui.theme.OrangePrimary
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme


@Composable
fun OrderButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth().height(52.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = OrangePrimary)
    ) {
        Text(
            text = text,
            color = Color.White.copy(alpha = 1f),
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OrderButtonPreview() {
    SubmissionComposeTheme {
        OrderButton(
            text = "Order",
            onClick = {}
        )
    }
}