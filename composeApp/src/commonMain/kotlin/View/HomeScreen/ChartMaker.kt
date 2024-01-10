package View.HomeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ChartMaker(a: Int, b: Int) {
    // Calculate angles based on the sum of a and b
    val total = a + b
    val angleA = (360f * (a / total.toFloat())).coerceIn(0f, 360f)
    val angleB = (360f * (b / total.toFloat())).coerceIn(0f, 360f)

    Surface(
        modifier = Modifier.size(150.dp),
        color = Color.White
    ) {
        Canvas(modifier = Modifier.fillMaxSize().padding(25.dp)) {
            // Draw the first arc (red)
            drawArc(
                color = Color.Red,
                startAngle = 0f,
                sweepAngle = angleA,
                useCenter = false,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, size.height),
                style = Stroke(width = 8.dp.toPx())
            )

            // Draw the second arc (green)
            drawArc(
                color = Color.Green,
                startAngle = angleA,
                sweepAngle = angleB,
                useCenter = false,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, size.height),
                style = Stroke(width = 8.dp.toPx())
            )
        }
    }
}