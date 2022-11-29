package divyansh.tech.healthappudemy.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

fun Modifier.tinderLikeSwipe(
    maxWidth: Float,
    maxHeight: Float,
    onDragAccepted: () -> Unit = {},
    onDragRejected: () -> Unit = {}
): Modifier = composed {

    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    pointerInput(Unit) {
        coroutineScope {
            detectDragGestures(
                onDrag = { change, dragAmount ->
                    val original = Offset(offsetX.targetValue, offsetY.targetValue)
                    val summed = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-maxWidth, maxWidth),
                        y = summed.y.coerceIn(-maxHeight, maxHeight)
                    )

                    launch {
                        offsetX.animateTo(newValue.x)
                    }
                    launch {
                        offsetY.animateTo(newValue.y)
                    }
                },
                onDragEnd = {
                    when {
                        offsetX.targetValue > maxWidth * 0.55f -> {
                            launch {
                                offsetX.animateTo(maxWidth * 2, tween(400))
                            }.invokeOnCompletion { onDragAccepted() }
                        }
                        offsetX.targetValue < -(maxWidth * 0.55f) -> {
                            launch {
                                offsetX.animateTo(-(maxWidth * 2), tween(400))
                            }.invokeOnCompletion { onDragRejected() }
                        }
                        else -> {
                            launch {
                                offsetX.animateTo(0f)
                                offsetY.animateTo(0f)
                            }
                        }
                    }
                },
            )
        }
    }.graphicsLayer {
        translationX = offsetX.value
        translationY = offsetY.value
        rotationZ = (offsetX.value / 60).coerceIn(-100f, 100f)
        alpha = ((maxWidth - abs(offsetX.value)) / maxWidth).coerceIn(0f, 1f)
    }
}