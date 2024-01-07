package View.LoginSignup

import AppState
import Theme.AppColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twacha.MR

@Composable
fun SignIn(navigateToAnotherScreen: (AppState) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header()
            EmailTextField()
            PasswordTextField()
            ForgotPasswordTextField()
            SignInButton(navigateToAnotherScreen)
            DividerWithText()
            Row(
               modifier = Modifier.fillMaxWidth(0.5f)
                   .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                GoogleLogin()
                FacebookLogin()
            }

        }
    }
}

@Composable
fun SignInButton(navigateToAnotherScreen: (AppState) -> Unit) {
    Column () {
        Button(
            onClick = { navigateToAnotherScreen(AppState.SIGNINSCREEN) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColor.PURPLE
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(20.dp, 40.dp, 20.dp, 0.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(3.dp),
                text = "Sign In",
                fontSize = 16.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
fun ForgotPasswordTextField() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Forgot Password?")
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ClickableText(
            modifier = Modifier
                .padding(0.dp, 0.dp, 25.dp, 0.dp)
                .align(Alignment.End),
            text = annotatedString,
            onClick = { offset ->

            }
        )
    }
}

@Composable
fun Header() {
    Text(
        modifier = Modifier
            .padding(0.dp, 100.dp, 0.dp, 10.dp),
        text = "Sign In",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    )
    Text(
        modifier = Modifier,
        text = "Hi! Sign in/ Sign up to continue",
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
    )
}

@Composable
fun EmailTextField() {
    var email by remember { mutableStateOf("") }

    OutlinedTextField(
        value = email,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            textColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        ),
        onValueChange = { email = it },
        label = { Text("Email") },
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        modifier = Modifier.fillMaxWidth()
            .padding(30.dp, 40.dp, 30.dp, 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun PasswordTextField() {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            textColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        ),
        onValueChange = { password = it },
        label = { Text("Password") },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
            .padding(30.dp, 5.dp, 30.dp, 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun DividerWithText() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(0.dp, 50.dp, 0.dp, 0.dp)
    ) {
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "Or sign in with",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .background(Color.White)
                .align(Alignment.Center)
            ,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun GoogleLogin() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = CircleShape
            ),
    ) {
        Image(
            painter = dev.icerock.moko.resources.compose.painterResource(MR.images.google),
            contentDescription = null
        )
    }
}

@Composable
fun FacebookLogin() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = CircleShape
            ),
    ) {
        Image(
            painter = dev.icerock.moko.resources.compose.painterResource(MR.images.facebook),
            contentDescription = null
        )
    }
}