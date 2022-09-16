package divyansh.tech.healthapp.onboarding

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import divyansh.tech.healthapp.onboarding.AvatarPicker.AvatarPickerScreen
import divyansh.tech.healthapp.onboarding.GenderPicker.GenderPickerScreen
import divyansh.tech.healthapp.onboarding.HeightPicker.HeightPickerScreen
import divyansh.tech.healthapp.onboarding.WeightPicker.WeightPickerScreen
import divyansh.tech.healthappudemy.MainViewModel


enum class OnboardingSections(
    val title: String,
    val icon: ImageVector = Icons.Outlined.Delete,
    val route: String
) {
    GENDER("Gender", route = "onboarding/gender"),
    AVATAR("Avatar", route = "onboarding/avatar"),
    HEIGHT("Height", route = "onboarding/height"),
    WEIGHT("Weight", route = "onboarding/weight")
}

const val ONBOARDING_NAV_GRAPH = "ONBOARDING_NAV_GRAPH"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.OnboardingScreenNavGraph(
    navHostController: NavHostController,
    viewModel: MainViewModel
) {
    viewModel.setShouldStepBeVisible(true)
    navigation(
        startDestination = OnboardingSections.GENDER.route,
        route = ONBOARDING_NAV_GRAPH
    ) {
        composable(OnboardingSections.GENDER.route) {
            LaunchedEffect(Unit) {
                viewModel.setCurrentStep(1)
            }
            GenderPickerScreen(
                navHostController,
                1
            )
        }
        composable(
            OnboardingSections.WEIGHT.route,
        ) {
            LaunchedEffect(Unit) {
                viewModel.setCurrentStep(2)
            }
            WeightPickerScreen(navHostController, 2)
        }
        composable(
            OnboardingSections.HEIGHT.route,
        ) {
            LaunchedEffect(Unit) {
                viewModel.setCurrentStep(3)
            }
            HeightPickerScreen(navHostController, 3)
        }
        composable(
            OnboardingSections.AVATAR.route,
        ) {
            LaunchedEffect(Unit) {
                viewModel.setCurrentStep(4)
            }
            AvatarPickerScreen(navHostController, 4)
        }
    }
}
