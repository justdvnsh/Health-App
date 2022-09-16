package divyansh.tech.healthapp.onboarding.WeightPicker

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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import divyansh.tech.healthapp.onboarding.OnboardingSections
import divyansh.tech.healthappudemy.R

@Composable
fun WeightPickerScreen(
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
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.weight)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true,
            speed = 1f,
            restartOnPlay = true
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LottieAnimation(composition, progress = {progress}, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Spacer(modifier = Modifier.padding(12.dp))
        Text(text = "Step ${currentStep}/4", fontSize = 12.sp, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = "What is your weight ?", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.padding(12.dp))

    }
}

@Composable
fun Middle() {
    Column(
        modifier = Modifier.wrapContentSize()
    ) {

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
                navController.navigate(OnboardingSections.HEIGHT.route) {

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