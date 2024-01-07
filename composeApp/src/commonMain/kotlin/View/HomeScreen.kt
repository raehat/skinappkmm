package View

import ScreenState
import Theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(navigateToAnotherScreen: (ScreenState) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(AppColor.LIGHT_GRAY)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.1f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
            ,
        ) {}

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .align(Alignment.BottomCenter)
                ,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White,
                elevation = 8.dp
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "Your Personal Skin Specialist",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .padding(0.dp, 10.dp, 0.dp, 0.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "powered by AI",
                        style = TextStyle(
                            color = AppColor.PURPLE,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Unveiling Healthy Skin with Smart Detection and Expert Advice",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .padding(15.dp, 20.dp, 15.dp, 0.dp),
                        textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = { navigateToAnotherScreen(ScreenState.SIGNINSCREEN) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppColor.PURPLE
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(0.dp, 40.dp, 0.dp, 0.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Let's Get Started",
                            color = Color.White,
                        )
                    }

                }
            }
    }
}