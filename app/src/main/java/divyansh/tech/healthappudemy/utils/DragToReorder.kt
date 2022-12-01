package divyansh.tech.healthappudemy.utils

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntOffset
import divyansh.tech.healthappudemy.home.ListScreen.SlideState
import divyansh.tech.healthappudemy.home.ListScreen.model.ListItem
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.math.sign

fun Modifier.dragToReorder(
    listItem: ListItem,
    items: MutableList<ListItem>,
    itemHeight: Int,
    updateSlidedState: (listItem: ListItem, slideState: SlideState) -> Unit,
    onDrag: () -> Unit,
    onStopDrag: (currentIndex: Int, destinationIndex: Int) -> Unit,
): Modifier = composed {

    val offsetY = remember { Animatable(0f) }
    pointerInput(Unit) {
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                // Interrupt any ongoing animation of other items.
                offsetY.stop()

                val listItemIndex = items.indexOf(listItem)
                val offsetToSlide = itemHeight / 2
                var numberOfItems = 0

                // Wait for drag events.
                awaitPointerEventScope {
                    verticalDrag(pointerId) { change ->
                        onDrag()
                        val verticalDragOffset = offsetY.value + change.positionChange().y

                        launch {
                            offsetY.snapTo(verticalDragOffset)
                            val offsetSign = offsetY.value.sign.toInt()
                            numberOfItems = calculateNumberOfSlidItems(
                                offsetY.value * offsetSign,
                                itemHeight,
                                offsetToSlide,
                            )

                            if (numberOfItems != 0) {
                                updateSlidedState(
                                    items[listItemIndex + numberOfItems * offsetSign],
                                    if (offsetSign == 1) SlideState.UP else SlideState.DOWN
                                )
                            }
                        }// Consume the gesture event, not passed to external
                        change.consumePositionChange()
                    }
                }
                launch {
                    offsetY.animateTo(numberOfItems * offsetY.value.sign)
                    onStopDrag(listItemIndex, listItemIndex + (numberOfItems * offsetY.value.sign).toInt())
                }
            }
        }
    }.offset {
        // Use the animating offset value here.
        IntOffset(0, offsetY.value.roundToInt())
    }
}

private fun calculateNumberOfSlidItems(
    offsetY: Float,
    itemHeight: Int,
    offsetToSlide: Int,
//    previousNumberOfItems: Int
): Int {
    val numberOfItemsInOffset = (offsetY / itemHeight).toInt()
    val numberOfItemsPlusOffset = ((offsetY + offsetToSlide) / itemHeight).toInt()
    val numberOfItemsMinusOffset = ((offsetY - offsetToSlide - 1) / itemHeight).toInt()
    return when {
        offsetY - offsetToSlide - 1 < 0 -> 0
        numberOfItemsPlusOffset > numberOfItemsInOffset -> numberOfItemsPlusOffset
        numberOfItemsMinusOffset < numberOfItemsInOffset -> numberOfItemsInOffset
        else -> 0
    }
}