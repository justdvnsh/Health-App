package divyansh.tech.healthappudemy.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

class CutCornerRoundedShape(private val cornerRadius: Float): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawBottomCutCornerRoundedShape(size = size, cornerRadius = cornerRadius)
        )
    }

    private fun drawBottomCutCornerRoundedShape(size: Size, cornerRadius: Float): Path {
        return Path().apply {
            moveTo(cornerRadius, 0f)
//            // Top left arc
            lineTo(x = size.width - cornerRadius, y = 0f)
            // Top right arc
            quadraticBezierTo(
                size.width, 0f,
                size.width, cornerRadius
            )
            lineTo(x = size.width, y = size.height - cornerRadius)
            // Bottom right arc
            addArc(
                oval = Rect(
                    left = size.width - cornerRadius,
                    top = size.height - cornerRadius,
                    right = size.width + cornerRadius,
                    bottom = size.height + cornerRadius
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = -90f
            )

            lineTo(x = cornerRadius, y = size.height)
            // Bottom left arc
            quadraticBezierTo(
                0f, size.height,
                0f, size.height - cornerRadius
            )
            lineTo(x = 0f, y = cornerRadius)
            quadraticBezierTo(
                0f, 0f,
                cornerRadius, 0f
            )
        }
    }
}

class PolyShape(private val sides: Int, private val radius: Float) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(
            path = drawPolygon(sides, radius, size.center)
        )
    }
}

fun drawPolygon(sides: Int, radius: Float, center: Offset): Path {
    val angle = (360 / sides) * (Math.PI / 180)
    return Path().apply {
        moveTo(
            x = center.x + radius,
            y = center.y
        )
        for (i in 1 until sides) {
            lineTo(
                x = center.x + (radius * cos(angle * i)).toFloat(),
                y = center.y + (radius * sin(angle * i)).toFloat()
            )
        }
    }
}