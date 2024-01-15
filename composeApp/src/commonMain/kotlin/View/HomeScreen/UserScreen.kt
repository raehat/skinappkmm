package View.HomeScreen

import Data.Scan
import Data.Scans
import Network.getAllScans
import PhotoSelector.rememberBitmapFromBytes
import Theme.AppColor
import androidx.compose.foundation.Image
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun UserScreen(getEmail: () -> String?) {
    Box(
        modifier = Modifier
            .background(AppColor.LIGHT_GRAY)
    ) {
        var isProgressIndicatorVisible by remember { mutableStateOf(true) }
        val strokeWidth = 5.dp

        if (isProgressIndicatorVisible) {
            CircularProgressIndicator(
                modifier = Modifier.drawBehind {
                    drawCircle(
                        Color.Blue,
                        radius = size.width / 2 - strokeWidth.toPx() / 2,
                        style = Stroke(strokeWidth.toPx())
                    )
                }
                    .align(Alignment.Center),
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AboutApp()
            LastScans(getEmail) {
                isProgressIndicatorVisible = it
            }
            ScanHistory(getEmail)
        }
    }
}

@Composable
fun ScanHistory(getEmail: () -> String?) {
    var scans by remember { mutableStateOf(Scans()) }
    var a by remember { mutableStateOf(0) }
    var b by remember { mutableStateOf(0) }

    CoroutineScope(Dispatchers.IO).launch {
        val getScans = getAllScans(getEmail().toString())
        if (getScans != null) {
            scans = getScans
            a = scans.scans.size
        }
    }

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
                    a = a,
                    b = b
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    ScanHistoryText("Total photos uploaded", "${(a+b)}")
                    ScanHistoryText("without diagnosed problems", "$b")
                    ScanHistoryText("with diagnosed problems", "$a")
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
fun LastScans(getEmail: () -> String?, isProgressIndicatorVisible: (Boolean) -> Unit) {
    var scans by remember { mutableStateOf(Scans()) }
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
            CoroutineScope(Dispatchers.IO).launch {
                val getScans = getAllScans(getEmail().toString())
                if (getScans != null) {
                    scans = getScans
                }
                isProgressIndicatorVisible(false)
            }
            if (scans.scans.isEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 100.dp),
                    text = "No scans available"
                )
            }
            scans.scans.forEach {scan ->
                ScanItem(scan)
            }
        }
    }
}

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun ScanItem(scan: Scan) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        Column {
            val imageBitmap = rememberBitmapFromBytes(Base64.decode(scan.imageBase64))
            if (imageBitmap != null) {
                Image(
                    modifier = Modifier
                        .size(130.dp)
                        .padding(12.dp),
                    bitmap = imageBitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = "Scanned on",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 1.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = scan.date,
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
                text = scan.analysisResult,
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
