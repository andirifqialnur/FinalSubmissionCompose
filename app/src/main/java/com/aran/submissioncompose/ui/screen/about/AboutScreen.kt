package com.aran.submissioncompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aran.submissioncompose.R
import com.aran.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lime),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .border(width = 5.dp, color = Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.padding(7.dp))
        Text(
            text = stringResource(R.string.developer_name),
            style = MaterialTheme.typography.h3.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.padding(7.dp))
        Text(
            text = stringResource(R.string.job),
            fontSize = 25.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.padding(7.dp))
        Text(
            text = "Strawberry, (genus Fragaria), genus of more than 20 species of flowering plants in the rose family (Rosaceae) and their edible fruit. Strawberries are native to the temperate regions of the Northern Hemisphere, and cultivated varieties are widely grown throughout the world. The fruits are rich in vitamin C and are commonly eaten fresh as a dessert fruit, are used as a pastry or pie filling, and may be preserved in many ways. Strawberry shortcake—made of fresh strawberries, sponge cake, and whipped cream—is a traditional American dessert.",
            fontSize = 12.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    SubmissionComposeTheme {
        AboutScreen()
    }
}