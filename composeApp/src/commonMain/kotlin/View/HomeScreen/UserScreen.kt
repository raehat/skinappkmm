package View.HomeScreen

import Theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.ImageBitmap
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
            ScanHistory()
        }
    }
}

@Composable
fun ScanHistory() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 14.dp),
                text = "Analysis of previous scans",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ChartMaker(
                    a = 5,
                    b = 13
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    ScanHistoryText("Total photos uploaded", "18")
                    ScanHistoryText("without diagnosed problems", "13")
                    ScanHistoryText("with diagnosed problems", "5")
                }
            }
        }
    }
}

@Composable
fun ScanHistoryText(text: String, number: String) {
    Text(
        text = text,
        fontSize = 12.sp
    )
    Text(
        text = number
    )
}

@Composable
fun LastScans() {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
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
        Column {
            KamelImage(
                modifier = Modifier
                    .size(130.dp)
                    .padding(12.dp),
                resource = asyncPainterResource(data = "https://www.health.com/thmb/URmij5hdhBwrcESJFg9ySAlJLew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/VesiclesGettyImages-1176815581-8d5b59c3e24543cdb32bdd1d7b054482.jpg"),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Scanned on",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 1.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "08/01/24",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = "Result: ",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 2.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Probiotic lacmisus",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier
                .padding(bottom = 2.dp))
        }
    }
}

@Composable
fun AboutApp() {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp),
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
                        "increasing the chances of successful outcomes. ",
//                        "Ignoring unusual moles might lead to more serious" +
//                        " health issues, so it's crucial to be proactive " +
//                        "about our skin health. ",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}
