package divyansh.tech.healthappudemy

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import divyansh.tech.healthapp.onboarding.OnboardingScreenNavGraph
import divyansh.tech.healthappudemy.home.HOME_NAV_GRAPH
import divyansh.tech.healthappudemy.home.HomeFeedScreen.HomeFeedScreen
import divyansh.tech.healthappudemy.home.HomeNavGraph
import divyansh.tech.healthappudemy.home.HomeSections
import divyansh.tech.healthappudemy.home.ListScreen.ListScreen
import divyansh.tech.healthappudemy.home.SwipeScreen.SwipeScreen
import divyansh.tech.healthappudemy.ui.theme.HealthAppUdemyTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navHostController = rememberAnimatedNavController()
            val bottomBarScreens = listOf(HomeSections.HOME, HomeSections.SEARCH, HomeSections.SURVEY)

            val viewModel by viewModels<MainViewModel>()

            HealthAppUdemyTheme {
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            screens = bottomBarScreens,
                            navHostController = navHostController )
                    }
                ) {
                    AnimatedNavHost(
                        navController = navHostController,
                        startDestination = HOME_NAV_GRAPH,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = when (viewModel.getBottomBarVisible()) {
                                true -> 90.dp
                                false -> 0.dp
                            }),
                        enterTransition = {
                            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                        },
                        popEnterTransition = {
                            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                        },
                        popExitTransition = {
                            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                        }
                    ) {
                        OnboardingScreenNavGraph(
                            navHostController = navHostController,
                            viewModel = viewModel
                        )
                        HomeNavGraph(
                            navController = navHostController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

enum class BottomBarScreens(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Outlined.Home, "home"),
    LIST("List", Icons.Outlined.List, "list"),
    SWIPE("Swipe", Icons.Outlined.Share, "share")
}

@Composable
fun BottomBar(
    screens: List<HomeSections>,
    navHostController: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = "")},
                selected = screen.route == currentRoute,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.LightGray,
                onClick = {
                    navHostController.navigate(screen.route) {
                        navHostController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}
