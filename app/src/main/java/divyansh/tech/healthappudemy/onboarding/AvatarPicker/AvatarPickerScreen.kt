package divyansh.tech.healthapp.onboarding.AvatarPicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import divyansh.tech.healthappudemy.R
import kotlin.math.absoluteValue

@Composable
fun AvatarPickerScreen(
    navHostController: NavHostController,
    currentStep: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(10.dp)
            .verticalScroll(enabled = true, state = rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Top(currentStep)
        Middle()
        Bottom(
            navHostController
        )
    }
}

@Composable
fun Top(currentStep: Int) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Step ${currentStep}/4", fontSize = 12.sp, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = "Pick an Avatar for yourself", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Middle() {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = "", tint = MaterialTheme.colors.primary, modifier = Modifier
            .height(32.dp)
            .width(32.dp))
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        HorizontalPager(
            count = 3,
            modifier = Modifier.wrapContentSize(),
            contentPadding = PaddingValues(horizontal = 80.dp, vertical = 0.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .aspectRatio(1f)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = ScaleFactor(0.85f, 0.85f),
                            stop = ScaleFactor(1f, 1f),
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { it ->
                            scaleX = it.scaleX
                            scaleY = it.scaleY
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = ScaleFactor(0.5f, 0.5f),
                            stop = ScaleFactor(1f, 1f),
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).scaleX
                    },
                elevation = 0.dp,
                shape = RoundedCornerShape(100.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                val painter = when(page) {
                    0 -> painterResource(id = R.drawable.cat)
                    1 -> painterResource(id = R.drawable.dog)
                    2 -> painterResource(id = R.drawable.lion)
                    else -> painterResource(id = R.drawable.cat)
                }
                Image(
                    painter = painter,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = "", tint = MaterialTheme.colors.primary, modifier = Modifier
            .height(32.dp)
            .width(32.dp))
        Spacer(modifier = Modifier.padding(12.dp))
        Text(text = "You can select photo from one of this emoji or add your own photo as your profile picture", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.LightGray, textAlign = TextAlign.Center, modifier = Modifier.padding(18.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Bottom(
    navController: NavHostController
) {
    Column(modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colors.primaryVariant,
            contentColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {
                navController.navigate("") {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            Text(text = "Continue", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center)
        }
    }
}