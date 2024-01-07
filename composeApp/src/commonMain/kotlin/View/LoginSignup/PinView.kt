package View.LoginSignup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
@Composable
fun OtpView(
    count: Int = 0,
    onValueChange: (value: String) -> Unit
) {

    val requesterList: ArrayList<FocusRequester> = arrayListOf()
    val textList: ArrayList<MutableState<TextFieldValue>> = arrayListOf()
    var lastIndex = -1


    for (i in 0 until count) {

        // Add string mutableStateOf variables to the input count
        textList.add(
            remember {
                mutableStateOf(
                    TextFieldValue(
                        text = "",
                        selection = TextRange(0)
                    )
                )
            }
        )
        // Add FocusRequester object to the input count
        requesterList.add(FocusRequester())
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row {

            for (i in textList.indices) {
                InputView(
                    value = textList[i].value,
                    modifier = Modifier
                        .onFocusChanged {
                            // Focus on the priority cell when click in otp viewer
                            if (it.isFocused && lastIndex != i) {
                                lastIndex = i
                                setFocus(textList, requesterList)
                            }
                        }
                        .focusRequester(requesterList[i])
                        .onKeyEvent {
                            if (it.key == Key.Backspace) {

                                // Go to previous cell after clicking backspace
                                preFocus(i, textList, requesterList)
                                onValueChange(otp(textList))

                                true
                            } else {
                                false
                            }
                        }
                ) { newValue ->
                    if (newValue.text != "" && textList[i].value.text == "") {
                        textList[i].value = TextFieldValue(
                            text = newValue.text,
                            selection = TextRange(1)
                        )
                        // Go to next cell after type one digit
                        nextFocus(textList, requesterList)
                    }
                    onValueChange(otp(textList))
                }
            }
        }
    }
}

fun otp(textList: List<MutableState<TextFieldValue>>): String {

    var otp = ""
    for (index in textList.indices) {
        otp += textList[index].value.text
    }
    return otp
}


@Composable
fun InputView(
    modifier: Modifier,
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .width(48.dp)
            .height(48.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp),
            ),

        ) {
        BasicTextField(
            readOnly = false,
            value = value,
            modifier = modifier.clip(RoundedCornerShape(8.dp)),
            onValueChange = onValueChange,
            enabled = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = null
            )
        )
    }


}

fun setFocus(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>,
) {

    var founded = -1
    for (index in textList.indices) {
        if (textList[index].value.text == "") {
            requesterList[index].requestFocus()
            founded = index
            break
        }
    }
    if (founded != -1)
        requesterList[founded].requestFocus()
    else
        requesterList[requesterList.size - 1].requestFocus()
}

fun preFocus(
    index: Int,
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>,
) {

    if (index != 0 || textList[0].value.text != "")
        if (index in textList.indices && textList[index].value.text != "")
            textList[index].value = TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        else if (index in 1..textList.size - 2 || textList[index].value.text == "") {
            textList[index - 1].value = TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
            requesterList[index - 1].requestFocus()
        }

}


fun nextFocus(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>,
) {

    for (index in textList.indices) {
        if (textList[index].value.text == "") {
            if (index < textList.size) {
                requesterList[index].requestFocus()
            }
            break
        }
    }
}
