package View.HomeScreen

import Theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun UserScreen(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(AppColor.LIGHT_GRAY)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AboutApp()
            LastScans()
        }
    }
}

@Composable
fun LastScans() {
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp),
            text = "Previous scans"
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            ScanItem()
            ScanItem()
            ScanItem()
        }
    }
}

@Composable
fun ScanItem() {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        KamelImage(
            modifier = Modifier
                .size(150.dp),
            resource = asyncPainterResource(data = "https://www.health.com/thmb/URmij5hdhBwrcESJFg9ySAlJLew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/VesiclesGettyImages-1176815581-8d5b59c3e24543cdb32bdd1d7b054482.jpg"),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun AboutApp() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 14.dp),
                text = "Why Twacha?",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 20.dp),
                text = "Checking weird and suspicious skin moles or" +
                        " spots is important because it helps catch" +
                        " potential skin problems early. Some" +
                        " moles may change in color, size, or " +
                        "shape, which could be a sign of skin" +
                        " cancer.\n\n Getting them checked ensures " +
                        "timely detection and proper treatment, " +
                        "increasing the chances of successful outcomes. " +
                        "Ignoring unusual moles might lead to more serious" +
                        " health issues, so it's crucial to be proactive " +
                        "about our skin health. ",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}
