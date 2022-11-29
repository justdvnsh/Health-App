package divyansh.tech.healthappudemy.home.ListScreen.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import divyansh.tech.healthappudemy.R
import divyansh.tech.healthappudemy.home.ListScreen.model.CarbType
import divyansh.tech.healthappudemy.home.ListScreen.model.ListItem
import divyansh.tech.healthappudemy.ui.theme.*

@Composable
fun ListScreenItem(
    listItem: ListItem,
    modifier: Modifier
) {

    val displayText = when(listItem.carbType) {
        CarbType.VERY_LOW -> "VERY LOW CARB"
        CarbType.LOW -> "LOW CARB"
        CarbType.MED -> "MED CARB"
        CarbType.HIGH -> "HIGH CARB"
        CarbType.VERY_HIGH -> "VERY HIGH CARB"
    }
    val displayTextColor = when(listItem.carbType) {
        CarbType.VERY_LOW -> pink
        CarbType.LOW -> lightPurple
        CarbType.MED -> MaterialTheme.colors.primary
        CarbType.HIGH -> dogerBlue
        CarbType.VERY_HIGH -> fireRed
    }
    val emojiToDisplay = when(listItem.carbType) {
        CarbType.VERY_LOW -> painterResource(id = R.drawable.sad)
        CarbType.LOW -> painterResource(id = R.drawable.neutral)
        CarbType.MED -> painterResource(id = R.drawable.smiling)
        CarbType.HIGH -> painterResource(id = R.drawable.shocked)
        CarbType.VERY_HIGH -> painterResource(id = R.drawable.scared)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = listItem.painter),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp)
                        .clip(CutCornerRoundedShape(60f))
                )
                Image(
                    painter = emojiToDisplay,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(14.dp)
                        .width(14.dp)
                        .clip(CircleShape)
                        .align(Alignment.BottomEnd)
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(12.dp),
            ) {
                Text(text = listItem.text, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                Spacer(modifier = Modifier.padding(top = 6.dp))
                Text(text = listItem.cals, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.LightGray)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = displayText, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = displayTextColor)
            }
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Divider(
            color = Color.LightGray.copy(alpha = 0.5f),
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}