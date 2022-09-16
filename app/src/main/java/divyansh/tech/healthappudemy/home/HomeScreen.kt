package divyansh.tech.healthappudemy.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import divyansh.tech.healthapp.home.Feed.screens.CalorieScreen
import divyansh.tech.healthapp.home.Feed.screens.StepsScreen
import divyansh.tech.healthapp.home.Feed.screens.WaterScreen
import divyansh.tech.healthapp.home.Feed.screens.WeightScreen
import divyansh.tech.healthappudemy.MainViewModel
import divyansh.tech.healthappudemy.home.HomeFeedScreen.HomeFeedScreen
import divyansh.tech.healthappudemy.home.ListScreen.ListScreen
import divyansh.tech.healthappudemy.home.SwipeScreen.SwipeScreen
import java.util.*

enum class HomeSections(
    val title: String,
    val icon: ImageVector = Icons.Outlined.Delete,
    val route: String
) {
    HOME("Home", Icons.Outlined.Home, "home/feed"),
    SEARCH("Search", Icons.Outlined.Search, "home/search"),
    SURVEY("Survey", Icons.Outlined.Send, "home/survey"),
    CALORIE("Calorie", route = "home/calorie"),
    WEIGHT("Weight", route = "home/weight"),
    WATER("Water", route = "home/water"),
    STEPS("Steps", route = "home/steps")
}

const val HOME_NAV_GRAPH = "HOME_NAV_GRAPH"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.HomeNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    viewModel.setShouldStepBeVisible(false)
    navigation(
        startDestination = HomeSections.HOME.route,
        route = HOME_NAV_GRAPH
    ) {
        composable(HomeSections.HOME.route) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(true)
            }
            HomeFeedScreen(navController)
        }
        composable(HomeSections.SEARCH.route) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(true)
            }
            ListScreen(navController)
        }
        composable(HomeSections.SURVEY.route) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(true)
            }
            SwipeScreen()
        }
        composable(
            HomeSections.CALORIE.route
        ) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(false)
            }
            CalorieScreen(navController)
        }
        composable(
            HomeSections.WEIGHT.route
        ) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(false)
            }
            WeightScreen(navController)
        }
        composable(
            HomeSections.WATER.route
        ) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(false)
            }
            WaterScreen(navController)
        }
        composable(
            HomeSections.STEPS.route
        ) {
            LaunchedEffect(Unit) {
                viewModel.setBottomBarVisible(false)
            }
            StepsScreen(navController)
        }
    }
}
