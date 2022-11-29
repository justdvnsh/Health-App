package divyansh.tech.healthappudemy.home.HomeFeedScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import divyansh.tech.healthapp.home.Feed.MetricsCardView
import divyansh.tech.healthappudemy.R
import divyansh.tech.healthappudemy.home.HomeSections
import divyansh.tech.healthappudemy.ui.theme.PolyShape
import divyansh.tech.healthappudemy.ui.theme.dogerBlue
import divyansh.tech.healthappudemy.ui.theme.lightPurple
import divyansh.tech.healthappudemy.ui.theme.slaty

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeFeedScreen(
    navController: NavHostController
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
    BottomSheetState(BottomSheetValue.Collapsed)
    )

    var isHealthCardExpanded by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(text = "Metrics", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.Black)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                    ) {
                        item {
                            MetricsCardView(
                                headerText = "Calories",
                                lottieComposition = R.raw.calorie,
                                subtitleText = "500",
                                unitText = "cal",
                                lastUpdatedText = "3m",
                                backgroundColor = MaterialTheme.colors.primary,
                                textColor = Color.White,
                                onClick = {
                                    navController.navigate(HomeSections.CALORIE.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                        item {
                            MetricsCardView(
                                headerText = "Weight",
                                lottieComposition = R.raw.weight,
                                subtitleText = "58",
                                unitText = "kg",
                                lastUpdatedText = "3d",
                                backgroundColor = lightPurple,
                                textColor = Color.White,
                                onClick = {
                                    navController.navigate(HomeSections.WEIGHT.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                        item {
                            MetricsCardView(
                                headerText = "Water",
                                lottieComposition = R.raw.water,
                                subtitleText = "750",
                                unitText = "ml",
                                lastUpdatedText = "3d",
                                backgroundColor = dogerBlue,
                                textColor = Color.White,
                                onClick = {
                                    navController.navigate(HomeSections.WATER.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                        item {
                            MetricsCardView(
                                headerText = "Steps",
                                lottieComposition = R.raw.steps,
                                subtitleText = "9,890",
                                lastUpdatedText = "3d",
                                backgroundColor = slaty,
                                textColor = Color.White,
                                onClick = {
                                    navController.navigate(HomeSections.STEPS.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            },
            sheetPeekHeight = if (isHealthCardExpanded) maxHeight * 0.55f else maxHeight * 0.60f
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HomeFeedHeader()
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(
                            color = Color.LightGray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier
                            .height(55.dp)
                            .width(55.dp)
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = PolyShape(6, 90f)
                            ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "84", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center)
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Text(text = "Health Score", color = Color.Black, fontWeight = FontWeight.Black, fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(vertical = 6.dp))
                            Text(
                                text = "Based on your overall health test, your score is 84 and considered good. However, this does not mean that you have to stop. Work hard. Get even a better fitness and I'll meet you at the top. Don't stop until then. Work like a monster.",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeFeedHeader() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                .wrapContentSize()) {
                Text(text = "Tue 13 Oct", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = MaterialTheme.colors.primary)
                Text(text = "Hi, Grace", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentHeight()
                    .width(56.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun HomeSectionHeader(
    buttonText: String = "",
    navController: NavHostController,
    onButtonClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .height(42.dp)
                    .width(42.dp)
            )
        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colors.primary.copy(0.1f),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(onClick = onButtonClick)
        ) {
            Text(
                text = buttonText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
    ) {
        Text(text = "BOTTOM SHEET")
    }
}
