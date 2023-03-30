package net.gelbelninoteam.cosmospingame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoBoard(mainText: String, textInfo: String) {
    val shape = RoundedCornerShape(50)

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(90.dp)
            .background(MaterialTheme.colors.secondary, shape)
            .border(3.dp, MaterialTheme.colors.primaryVariant, shape)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter, modifier = Modifier
                .width(100.dp)
                .height(40.dp)
        ) {
            Text(
                text = mainText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .width(100.dp)
                .height(80.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(96.dp)
                    .background(MaterialTheme.colors.primaryVariant)
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .width(100.dp)
                .height(75.dp)
        ) {
            Text(text = textInfo, fontSize = 17.sp, color = Color.White)
        }
    }
}

@Preview
@Composable
fun PreviewInfoBoard() {
    InfoBoard(mainText = "Last Sum", textInfo = "1000000")
}