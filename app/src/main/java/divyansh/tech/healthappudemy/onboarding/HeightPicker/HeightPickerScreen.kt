package divyansh.tech.healthapp.onboarding.HeightPicker

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import divyansh.tech.healthapp.onboarding.OnboardingSections
import divyansh.tech.healthappudemy.R

@Composable
fun HeightPickerScreen(
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
        Text(text = "Step ${currentStep}/4", fontSize = 12.sp, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = "What is your height ?", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun Middle() {
    Box(
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            
            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "180cm", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.man)
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
                    .height(300.dp))
            }
        }
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
                navController.navigate(OnboardingSections.AVATAR.route) {

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