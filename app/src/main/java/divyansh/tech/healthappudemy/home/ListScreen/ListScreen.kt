package divyansh.tech.healthappudemy.home.ListScreen

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import divyansh.tech.healthappudemy.home.ListScreen.components.ListScreenItem
import divyansh.tech.healthappudemy.home.ListScreen.model.ListItem
import divyansh.tech.healthappudemy.utils.dragToReorder
import divyansh.tech.healthappudemy.utils.swipeToDismiss

private val viewModel = ListViewModel()

enum class SlideState {
    NONE,
    UP,
    DOWN
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    navController: NavHostController,
) {

    val lazyListState = rememberLazyListState()
    val slideStates = remember {
        mutableStateMapOf<ListItem, SlideState>().apply {
            viewModel.items.map { listItem ->
                listItem to SlideState.NONE
            }.toMap().also {
                putAll(it)
            }
        }
    }

    val isDragged = remember { mutableStateOf(false) }
    val zIndex = if (isDragged.value) 1.0f else 0.0f
    val currentIndex = remember { mutableStateOf(0) }
    val destinationIndex = remember { mutableStateOf(0) }

    val isPlaced = remember { mutableStateOf(false) }

    var itemHeight = 0

    with(LocalDensity.current) {
        itemHeight = 136.dp.toPx().toInt()
    }

    LaunchedEffect(isPlaced.value) {
        if (isPlaced.value) {
            if (currentIndex.value != destinationIndex.value) {
                val listItem = viewModel.items[currentIndex.value]
                viewModel.items.removeAt(currentIndex.value)
                viewModel.items.add(destinationIndex.value, listItem)
                slideStates.apply {
                    viewModel.items.map { listItem ->
                        listItem to SlideState.NONE
                    }.toMap().also {
                        putAll(it)
                    }
                }
            }
            isPlaced.value = false
        }
    }

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

                            val slideState = slideStates[item] ?: SlideState.NONE

                            val verticalTranslation by animateIntAsState(
                                targetValue = when (slideState) {
                                    SlideState.UP -> -itemHeight
                                    SlideState.DOWN -> itemHeight
                                    else -> 0
                                },
                            )

                            BoxWithConstraints() {
                                ListScreenItem(
                                    listItem = item,
                                    modifier = Modifier.dragToReorder(
                                        item,
                                        viewModel.items,
                                        itemHeight,
                                        { listItem, slideState: SlideState -> slideStates[listItem] = slideState },
                                        { isDragged.value = true },
                                        { cIndex, dIndex ->
                                            isDragged.value = false
                                            isPlaced.value = true
                                            currentIndex.value = cIndex
                                            destinationIndex.value = dIndex
                                        }
                                    ).offset { IntOffset(0, verticalTranslation) }
                                        .zIndex(zIndex)
                                )
                            }
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