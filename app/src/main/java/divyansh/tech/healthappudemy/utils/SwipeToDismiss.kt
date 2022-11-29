package divyansh.tech.healthappudemy.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.tween
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntOffset
import divyansh.tech.healthappudemy.home.ListScreen.model.ListItem
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

//fun Modifier.swipeToDismissMethodOne(
//    item: ListItem,
//    maxWidth: Float,
//    onDismissed: (item: ListItem) -> Unit
//): Modifier = composed {
//
//    val offsetX = remember { Animatable(0f) }
//
//    pointerInput(Unit) {
//        coroutineScope {
//            detectDragGestures(
//                onDrag = {_, dragAmount ->
//                    if (dragAmount.x > 0) {
//                        val original = offsetX.targetValue
//                        val summed = original + dragAmount.x
//                        val newValue = summed.coerceIn(-maxWidth, maxWidth)
//
//                        launch {
//                            offsetX.animateTo(newValue)
//                        }
//                    }
//                },
//                onDragEnd = {
//                    if (offsetX.targetValue > maxWidth * 0.5f) {
//                        launch {
//                            offsetX.animateTo(maxWidth * 2, tween(400))
//                        }.invokeOnCompletion {
//                            onDismissed(item)
//                        }
//                    } else {
//                        launch {
//                            offsetX.snapTo(0f)
//                        }
//                    }
//                }
//            )
//        }
//    }.offset { IntOffset(offsetX.value.roundToInt(), 0) }
//
//}

fun Modifier.swipeToDismiss(
    item: ListItem,
    onDismissed: (item: ListItem) -> Unit
): Modifier = composed {

    val offsetX = remember { Animatable(0f) }

    pointerInput(Unit) {

        val decay = splineBasedDecay<Float>(this)

        coroutineScope {

            val pointerId = awaitPointerEventScope { awaitFirstDown().id }

            val velocityTracker = VelocityTracker()

            awaitPointerEventScope {
                horizontalDrag(pointerId) { change ->
                    val horizontalDragOffset = offsetX.targetValue + change.positionChange().x
                    val newValue = horizontalDragOffset.coerceIn(
                        -size.width.toFloat(),
                        size.width.toFloat()
                    )

                    velocityTracker.addPosition(change.uptimeMillis, change.position)

                    launch {
                        offsetX.animateTo(newValue)
                    }
                }
            }

            val velocity = velocityTracker.calculateVelocity().x

            val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)

            launch {
                if (targetOffsetX.absoluteValue <= size.width / 2f) {
                    // Not enough velocity; Slide back to the default position.
                    offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                } else {
                    // Enough velocity to slide away the element to the edge.
                    offsetX.animateDecay(velocity, decay)
                    // The element was swiped away.
                    onDismissed(item)
                }
            }

        }
    }.offset { IntOffset(offsetX.value.roundToInt(), 0) }
}