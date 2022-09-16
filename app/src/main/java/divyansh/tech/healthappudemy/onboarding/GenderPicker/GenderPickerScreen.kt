package divyansh.tech.healthapp.onboarding.GenderPicker

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import divyansh.tech.healthapp.onboarding.OnboardingSections
import divyansh.tech.healthappudemy.R


@Composable
fun GenderPickerScreen(
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
        Bottom(navHostController)
    }
}

@Composable
fun Top(currentStep: Int) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Step ${currentStep}/4", fontSize = 12.sp, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(12.dp))
        Text(text = "Which one are you ?", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun Middle() {
    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        GenderCards()
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Text(text = "To give you a customized experience we would need to know your gender", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.LightGray, textAlign = TextAlign.Center)
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
                navController.navigate(OnboardingSections.WEIGHT.route) {

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
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Text(text = "Prefer not to choose", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary, textAlign = TextAlign.Center)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenderCards(
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick
    ) {
    }
}

@Composable
fun GenderCard(
    type: Genders,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp),
        elevation = 4.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    when {
                        selected -> MaterialTheme.colors.primary.copy(alpha = 0.6f)
                        else -> Color.White
                    }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(
                when(type) {
                    Genders.MALE -> LottieCompositionSpec.RawRes(R.raw.male)
                    Genders.FEMALE -> LottieCompositionSpec.RawRes(R.raw.female)
                }
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                isPlaying = true,
                speed = 1f,
                restartOnPlay = true
            )
            LottieAnimation(composition, progress = {progress}, modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            Text(text = type.name, fontSize = 14.sp, fontWeight = FontWeight.Bold,
                color = when {
                    selected -> Color.White
                    else -> Color.LightGray
                }
            )
        }
    }
}

enum class Genders {
    MALE, FEMALE
}
