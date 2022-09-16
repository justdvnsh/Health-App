package divyansh.tech.healthapp.home.Feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import divyansh.tech.healthapp.onboarding.GenderPicker.Genders
import divyansh.tech.healthappudemy.R

@Composable
fun MetricsCardView(
    modifier: Modifier = Modifier,
    headerText: String = "",
    lottieComposition: Int = R.raw.water,
    subtitleText: String = "",
    unitText: String = "",
    lastUpdatedText: String = "",
    backgroundColor: Color = MaterialTheme.colors.background,
    textColor: Color = Color.Black,
    shape: Shape = RoundedCornerShape(10.dp),
    elevation: Dp = 0.dp,
    onClick: () -> Unit = {}
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieComposition)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true
    )

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(6.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        shape = shape,
        elevation = elevation,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = headerText, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor)
            LottieAnimation(composition, progress = {progress}, modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(
                    fontSize = 18.sp,
                    color = textColor
                )) {
                    append(subtitleText)
                }
                append(unitText)
            }, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor)
            Text(text = "last update at $lastUpdatedText", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = textColor.copy(alpha = 0.6f))
        }
    }
}