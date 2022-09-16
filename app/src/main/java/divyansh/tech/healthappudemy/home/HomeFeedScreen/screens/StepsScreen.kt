package divyansh.tech.healthapp.home.Feed.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import divyansh.tech.healthappudemy.home.HomeFeedScreen.BottomSheetContent
import divyansh.tech.healthappudemy.home.HomeFeedScreen.HomeSectionHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StepsScreen(
    navController: NavHostController
) {
    val bottomSheetScaffoldState = rememberModalBottomSheetState(initialValue =
    ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetShape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp),
        sheetState = bottomSheetScaffoldState,
        sheetElevation = 4.dp,
        sheetBackgroundColor = MaterialTheme.colors.primary
    ) {
        Scaffold(
            topBar = {
                HomeSectionHeader("Insight", navController, onButtonClick = {
                    scope.launch {
                        bottomSheetScaffoldState.show()
                    }
                })
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Daily Steps", fontSize = 14.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = MaterialTheme.colors.primary)
                    Text(text = buildAnnotatedString {
                        append("You have walked")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary
                            )
                        ) {
                            append(" 40% ")
                        }
                        append("of your goal")
                    }, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.h4.fontSize, color = Color.Black, modifier = Modifier.padding(horizontal = 20.dp), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    }
                }
            }
        }
    }
}