package com.haman.codelab2nd

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haman.codelab2nd.ui.theme.CodeLab2NdTheme

// Intrinsics
// (min|max)IntrinsicWidth : Given this height, what's the minimum/maximum width
// you can paint your content properly
// (min|max)IntrinsicHeight : Given this width, what's the minimum/maximum height
// you can paint you content properly

// 1. divide two texts with middle line
@Composable
fun TwoTexts (modifier : Modifier = Modifier, text1 : String, text2 : String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            text = text1,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
        )

        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Text(
            text = text2,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Preview(name = "two texts", showBackground = true)
@Composable
fun TwoTextsPreview () {
    CodeLab2NdTheme() {
        Surface() {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}