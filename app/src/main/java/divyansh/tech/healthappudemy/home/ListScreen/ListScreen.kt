package divyansh.tech.healthappudemy.home.ListScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import divyansh.tech.healthappudemy.home.ListScreen.components.ListScreenItem
import divyansh.tech.healthappudemy.home.ListScreen.model.ListItem

private val viewModel = ListViewModel()

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    navController: NavHostController,
) {

    val lazyListState = rememberLazyListState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
    BottomSheetState(BottomSheetValue.Collapsed)
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
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
                    LazyColumn(state = lazyListState) {
                        items(viewModel.items, key = {item: ListItem ->  item.id}) {item ->
                            ListScreenItem(listItem = item)
                        }
                    }
                }
            },
            sheetPeekHeight = maxHeight * 0.83f
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

            }
        }
    }
}